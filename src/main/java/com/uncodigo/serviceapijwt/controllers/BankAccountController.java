package com.uncodigo.serviceapijwt.controllers;

import com.uncodigo.serviceapijwt.dtos.BankAccountDto;
import com.uncodigo.serviceapijwt.dtos.BankAccountWithTransactionsDto;
import com.uncodigo.serviceapijwt.dtos.TransactionDto;
import com.uncodigo.serviceapijwt.models.BankAccount;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.services.IBankAccountService;
import com.uncodigo.serviceapijwt.services.ISecurityContextService;
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
    private final ISecurityContextService securityContextService;

    public BankAccountController(IBankAccountService bankAccountService, IUserService userService, ISecurityContextService securityContextService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.securityContextService = securityContextService;
    }

    @GetMapping({"/", ""})
    public BankAccountDto getBankAccounts() {

        // Buscar el usuario en la base de datos
        User user = securityContextService.getUserAuthenticated();

        // Obtener la cuenta bancaria del usuario
        BankAccount bankAccount = user.getBankAccount();

        log.info("Getting bank account for user: {}", user.getEmail());

        // Devolver la cuenta bancaria
        return BankAccountDto.fromBankAccount(bankAccount);
    }
}
