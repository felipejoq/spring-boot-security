package com.uncodigo.serviceapijwt.services.impl;

import com.uncodigo.serviceapijwt.dtos.PageResponseDto;
import com.uncodigo.serviceapijwt.dtos.TransactionCreateDto;
import com.uncodigo.serviceapijwt.dtos.TransactionDto;
import com.uncodigo.serviceapijwt.models.BankAccount;
import com.uncodigo.serviceapijwt.models.Transaction;
import com.uncodigo.serviceapijwt.repositories.ITransactionRepository;
import com.uncodigo.serviceapijwt.repositories.ITransactionTypeRepository;
import com.uncodigo.serviceapijwt.services.IBankAccountService;
import com.uncodigo.serviceapijwt.services.ITransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final ITransactionTypeRepository transactionTypeRepository;
    private final ITransactionRepository transactionRepository;
    private final IBankAccountService bankAccountService;

    public TransactionServiceImpl(ITransactionTypeRepository transactionTypeRepository, ITransactionRepository transactionRepository, IBankAccountService bankAccountService) {
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }


    @Override
    @Transactional
    public void deposit(TransactionCreateDto transactionDto) {
        BankAccount bankAccount = bankAccountService.findBankAccountByUserEmail(transactionDto.getAccountEmailSender()).orElse(null);
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(transactionTypeRepository.findById(2).orElse(null));
        transaction.setReceiver(null);
        transaction.setSender(bankAccount);
        transaction.setTotal(transactionDto.getAmount());

        if (bankAccount != null) {
            bankAccountService.addAmountToBalance(transaction);
        }

        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void withdraw(TransactionCreateDto transactionDto) {
        BankAccount bankAccount = bankAccountService.findBankAccountByUserEmail(transactionDto.getAccountEmailSender()).orElse(null);

        if (bankAccount != null && bankAccount.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(transactionTypeRepository.findById(3).orElse(null));
        transaction.setReceiver(null);
        transaction.setSender(bankAccount);
        transaction.setTotal(transactionDto.getAmount());

        if (bankAccount != null) {
            bankAccountService.subtractAmountFromBalance(transaction);
        }

        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void transfer(TransactionCreateDto transferDto) {
        BankAccount sender = bankAccountService.findBankAccountByUserEmail(transferDto.getAccountEmailSender()).orElse(null);
        BankAccount receiver = bankAccountService.findBankAccountByUserEmail(transferDto.getAccountEmailReceiver()).orElse(null);
        if (sender == null) {
            throw new IllegalArgumentException("Cuenta de origen no encontrada");
        }
        if (sender.getBalance().compareTo(transferDto.getAmount()) < 0) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        if (receiver == null) {
            throw new IllegalArgumentException("Cuenta de destino no encontrada");
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(transactionTypeRepository.findById(1).orElse(null));
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transaction.setTotal(transferDto.getAmount());

        bankAccountService.subtractAmountFromBalance(transaction);

        transactionRepository.save(transaction);
    }

    @Override
    public PageResponseDto<TransactionDto> getTransactionsByUserEmail(String userEmail, Pageable pageable) {
        BankAccount bankAccount = bankAccountService.findBankAccountByUserEmail(userEmail).orElse(null);
        if (bankAccount == null) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }

        Page<Transaction> transactions = transactionRepository.findTransactionsByAccountId(bankAccount.getId(), pageable);

        Page<TransactionDto> transactionDtos = transactions.map(TransactionDto::fromTransaction);

        return new PageResponseDto<>(transactionDtos);
    }

}
