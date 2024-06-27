package com.uncodigo.serviceapijwt.dtos;

import com.uncodigo.serviceapijwt.models.BankAccount;

public class BankAccountDto {

    private Integer id;
    private String accountNumber;
    private CurrencyDto currency;

    public BankAccountDto(Integer id, String accountNumber, CurrencyDto currency) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.currency = currency;
    }

    public static BankAccountDto fromBankAccount(BankAccount bankAccount) {
        CurrencyDto currencyDto = CurrencyDto.fromCurrency(bankAccount.getCurrency());
        return new BankAccountDto(bankAccount.getId(), bankAccount.getAccountNumber(), currencyDto);
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

    public CurrencyDto getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDto currency) {
        this.currency = currency;
    }
}
