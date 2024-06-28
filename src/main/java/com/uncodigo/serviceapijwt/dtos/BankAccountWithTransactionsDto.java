package com.uncodigo.serviceapijwt.dtos;

import com.uncodigo.serviceapijwt.models.BankAccount;

import java.math.BigDecimal;
import java.util.Collection;

public class BankAccountWithTransactionsDto extends BankAccountDto {
    private Collection<TransactionDto> transactions;

    public BankAccountWithTransactionsDto(
            Integer id,
            String accountNumber,
            UserDto owner,
            CurrencyDto currency,
            Collection<TransactionDto> transactions,
            BigDecimal balance
    ) {
        super(id, accountNumber, owner, currency, balance);
        this.transactions = transactions;
    }

    public static BankAccountWithTransactionsDto fromBankAccountAndTransactions(BankAccount bankAccount, Collection<TransactionDto> transactionDtos) {
        CurrencyDto currencyDto = CurrencyDto.fromCurrency(bankAccount.getCurrency());
        return new BankAccountWithTransactionsDto(
                bankAccount.getId(),
                bankAccount.getAccountNumber(),
                UserDto.fromUser(bankAccount.getOwner()),
                currencyDto,
                transactionDtos,
                bankAccount.getBalance()
        );
    }

    public Collection<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}