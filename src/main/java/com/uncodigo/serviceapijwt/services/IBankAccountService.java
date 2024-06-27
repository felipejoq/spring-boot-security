package com.uncodigo.serviceapijwt.services;

import com.uncodigo.serviceapijwt.models.BankAccount;
import com.uncodigo.serviceapijwt.models.User;

public interface IBankAccountService {
    BankAccount createBankAccount(User user);
}
