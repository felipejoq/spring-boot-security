package com.uncodigo.serviceapijwt.controllers;

import com.uncodigo.serviceapijwt.dtos.BankAccountWithTransactionsDto;
import com.uncodigo.serviceapijwt.dtos.TransactionDto;
import com.uncodigo.serviceapijwt.models.BankAccount;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.services.IBankAccountService;
import com.uncodigo.serviceapijwt.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

    private static final Logger log = LoggerFactory.getLogger(BankAccountController.class);
    private final IBankAccountService bankAccountService;
    private final IUserService userService;

    public BankAccountController(IBankAccountService bankAccountService, IUserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public BankAccountWithTransactionsDto getBankAccounts() {
        // Obtener el nombre de usuario del token
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        // Buscar el usuario en la base de datos
        User user = userService.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        // Obtener la cuenta bancaria del usuario
        BankAccount bankAccount = user.getBankAccount();

        // Convertir las transacciones a DTOs
        Collection<TransactionDto> transactionDtos = bankAccount.getTransaction().stream()
                .map(TransactionDto::fromTransaction)
                .toList();

        log.info("Getting bank account for user: {}", username);

        // Devolver la cuenta bancaria
        return BankAccountWithTransactionsDto.fromBankAccountAndTransactions(bankAccount, transactionDtos);
    }
}
