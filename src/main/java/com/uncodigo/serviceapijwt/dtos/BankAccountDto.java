package com.uncodigo.serviceapijwt.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncodigo.serviceapijwt.models.BankAccount;

import java.math.BigDecimal;

public class BankAccountDto {

    private Integer id;
    @JsonProperty("account_number")
    private String accountNumber;
    private UserDto owner;
    private CurrencyDto currency;
    private BigDecimal balance;

    public BankAccountDto(Integer id, String accountNumber, UserDto owner, CurrencyDto currency, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.currency = currency;
        this.balance = balance;
    }

    public static BankAccountDto fromBankAccount(BankAccount bankAccount) {
        CurrencyDto currencyDto = CurrencyDto.fromCurrency(bankAccount.getCurrency());
        return new BankAccountDto(
                bankAccount.getId(),
                bankAccount.getAccountNumber(),
                UserDto.fromUser(bankAccount.getOwner()),
                currencyDto,
                bankAccount.getBalance()
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public CurrencyDto getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDto currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}