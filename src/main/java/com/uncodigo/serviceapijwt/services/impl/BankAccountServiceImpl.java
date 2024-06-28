package com.uncodigo.serviceapijwt.services.impl;

import com.uncodigo.serviceapijwt.models.BankAccount;
import com.uncodigo.serviceapijwt.models.Currency;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.repositories.IBankAccountRepository;
import com.uncodigo.serviceapijwt.repositories.ICurrencyRepository;
import com.uncodigo.serviceapijwt.services.IBankAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements IBankAccountService {

    private final IBankAccountRepository bankAccountRepository;
    private final ICurrencyRepository currencyRepository;

    public BankAccountServiceImpl(IBankAccountRepository bankAccountRepository, ICurrencyRepository currencyRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.currencyRepository = currencyRepository;
    }


    @Override
    public BankAccount createBankAccount(User user) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner(user);
        Currency currency = currencyRepository.findById(3L).orElse(null);
        bankAccount.setCurrency(currency);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount getBankAccount(User user) {
        // Si el usuario no tiene cuenta bancaria, se crea una nueva
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findByOwner_Id(user.getId());
        return optionalBankAccount.orElseGet(() -> createBankAccount(user));
    }
}
