package com.uncodigo.serviceapijwt.services;

import com.uncodigo.serviceapijwt.dtos.TransactionCreateDto;
import com.uncodigo.serviceapijwt.dtos.TransferDto;
import com.uncodigo.serviceapijwt.models.Transaction;

import java.util.Collection;

public interface ITransactionService {
    void deposit(TransactionCreateDto transactionDto);
    void withdraw(TransactionCreateDto transactionDto);
    void transfer(TransferDto transferDto);
    Collection<Transaction> getTransactionsByUserEmail(String userEmail);
}
