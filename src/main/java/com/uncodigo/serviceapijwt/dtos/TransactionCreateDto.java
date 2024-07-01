package com.uncodigo.serviceapijwt.dtos;

import java.math.BigDecimal;

public class TransactionCreateDto {
    private BigDecimal amount;
    private String accountEmail;

    public TransactionCreateDto() {
    }

    public TransactionCreateDto(BigDecimal amount, String accountEmail) {
        this.amount = amount;
        this.accountEmail = accountEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }
}
