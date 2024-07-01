package com.uncodigo.serviceapijwt.controllers;

import com.uncodigo.serviceapijwt.dtos.PageResponseDto;
import com.uncodigo.serviceapijwt.dtos.TransactionDto;
import com.uncodigo.serviceapijwt.models.Transaction;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.services.ISecurityContextService;
import com.uncodigo.serviceapijwt.services.ITransactionService;
import com.uncodigo.serviceapijwt.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
        // Buscar el usuario en la base de datos
        User user = securityContextService.getUserAuthenticated();

        // Obtener las transacciones del usuario
        return transactionService.getTransactionsByUserEmail(user.getEmail(), pageable);
    }

}
