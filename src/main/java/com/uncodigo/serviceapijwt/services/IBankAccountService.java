package com.uncodigo.serviceapijwt.services;

import com.uncodigo.serviceapijwt.models.BankAccount;
import com.uncodigo.serviceapijwt.models.Transaction;
import com.uncodigo.serviceapijwt.models.User;

import java.util.Optional;

public interface IBankAccountService {
    BankAccount createBankAccount(User user);
    BankAccount getBankAccount(User user);
    Optional<BankAccount> findBankAccountByUserEmail(String userEmail);
    void addAmountToBalance(Transaction transaction);
    void subtractAmountFromBalance(Transaction transaction);
}
