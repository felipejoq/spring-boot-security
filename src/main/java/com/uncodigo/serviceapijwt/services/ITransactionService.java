package com.uncodigo.serviceapijwt.services;

import com.uncodigo.serviceapijwt.dtos.PageResponseDto;
import com.uncodigo.serviceapijwt.dtos.TransactionCreateDto;
import com.uncodigo.serviceapijwt.dtos.TransactionDto;
import com.uncodigo.serviceapijwt.dtos.TransferDto;
import com.uncodigo.serviceapijwt.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITransactionService {
    void deposit(TransactionCreateDto transactionDto);
    void withdraw(TransactionCreateDto transactionDto);
    void transfer(TransferDto transferDto);
    PageResponseDto<TransactionDto> getTransactionsByUserEmail(String userEmail, Pageable pageable);
}
