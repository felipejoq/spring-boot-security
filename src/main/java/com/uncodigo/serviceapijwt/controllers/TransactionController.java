package com.uncodigo.serviceapijwt.controllers;

import com.uncodigo.serviceapijwt.dtos.PageResponseDto;
import com.uncodigo.serviceapijwt.dtos.TransactionCreateDto;
import com.uncodigo.serviceapijwt.dtos.TransactionDto;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.services.ISecurityContextService;
import com.uncodigo.serviceapijwt.services.ITransactionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final ITransactionService transactionService;
    private final ISecurityContextService securityContextService;

    public TransactionController(ITransactionService transactionService, ISecurityContextService securityContextService) {
        this.transactionService = transactionService;
        this.securityContextService = securityContextService;
    }

    @GetMapping({"/", ""})
    public PageResponseDto<TransactionDto> getTransactions(Pageable pageable) {
        // Obtener al usuario autenticado usando el servicio de contexto de seguridad
        User user = securityContextService.getUserAuthenticated();
        // Obtener las transacciones del usuario
        return transactionService.getTransactionsByUserEmail(user.getEmail(), pageable);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody TransactionCreateDto transactionDto) {
        HashMap<String, Object> response = new HashMap<>();
        User authenticatedUser = securityContextService.getUserAuthenticated();
        if (!authenticatedUser.getEmail().equals(transactionDto.getAccountEmailSender())) {
            response.put("message", "No puedes depositar en una cuenta que no es tuya. Utiliza la transferencia.");
            response.put("ok", false);
            return ResponseEntity.badRequest().body(response);
        }
        try {
            transactionService.deposit(transactionDto);
            response.put("message", "Depósito exitoso");
            response.put("ok", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al depositar: " + e.getMessage());
            response.put("ok", false);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransactionCreateDto transactionDto) {
        HashMap<String, Object> response = new HashMap<>();
        User authenticatedUser = securityContextService.getUserAuthenticated();

        if (!authenticatedUser.getEmail().equals(transactionDto.getAccountEmailSender())) {
            response.put("message", "No puedes retirar de una cuenta que no es tuya");
            response.put("ok", false);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            transactionService.withdraw(transactionDto);
            response.put("message", "Retiro exitoso");
            response.put("ok", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al retirar: " + e.getMessage());
            response.put("ok", false);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransactionCreateDto transferDto) {
        User authenticatedUser = securityContextService.getUserAuthenticated();

        if (!authenticatedUser.getEmail().equals(transferDto.getAccountEmailSender())) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "No puedes transferir desde una cuenta que no es tuya");
            response.put("ok", false);
            return ResponseEntity.badRequest().body(response);
        }

        HashMap<String, Object> response = new HashMap<>();
        try {
            transactionService.transfer(transferDto);
            response.put("message", "Transferencia exitosa");
            response.put("ok", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al transferir: " + e.getMessage());
            response.put("ok", false);
            return ResponseEntity.badRequest().body(response);
        }

    }

}
