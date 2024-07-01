package com.uncodigo.serviceapijwt.services.impl;

import com.uncodigo.serviceapijwt.models.*;
import com.uncodigo.serviceapijwt.repositories.IBankAccountRepository;
import com.uncodigo.serviceapijwt.repositories.ICurrencyRepository;
import com.uncodigo.serviceapijwt.services.IBankAccountService;
import com.uncodigo.serviceapijwt.types.TransactionTypes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Optional<BankAccount> findBankAccountByUserEmail(String userEmail) {
        return bankAccountRepository.findByOwner_Email(userEmail);
    }

    @Override
    @Transactional
    public void addAmountToBalance(Transaction transaction) {
        BankAccount sender = transaction.getSender();
        if (sender != null) {
            sender.setBalance(sender.getBalance().add(transaction.getTotal()));
            bankAccountRepository.save(sender);
        }
    }

    @Override
    @Transactional
    public void subtractAmountFromBalance(Transaction transaction) {
        BankAccount sender = transaction.getSender();
        if (sender != null) {
            sender.setBalance(sender.getBalance().subtract(transaction.getTotal()));
            bankAccountRepository.save(sender);
        }

        if(transaction.getTransactionType().getId() == 1) {
            BankAccount receiver = transaction.getReceiver();
            if (receiver != null) {
                receiver.setBalance(receiver.getBalance().add(transaction.getTotal()));
                bankAccountRepository.save(receiver);
            }
        }
    }
}
