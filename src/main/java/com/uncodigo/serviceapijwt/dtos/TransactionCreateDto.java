package com.uncodigo.serviceapijwt.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransactionCreateDto {
    private BigDecimal amount;
    @JsonProperty("account_email_sender")
    private String accountEmailSender;
    @JsonProperty("account_email_receiver")
    private String accountEmailReceiver;

    public TransactionCreateDto() {
    }

    public TransactionCreateDto(BigDecimal amount, String accountEmailSender, String accountEmailReceiver) {
        this.amount = amount;
        this.accountEmailSender = accountEmailSender;
        this.accountEmailReceiver = accountEmailReceiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountEmailSender() {
        return accountEmailSender;
    }

    public void setAccountEmailSender(String accountEmailSender) {
        this.accountEmailSender = accountEmailSender;
    }

    public String getAccountEmailReceiver() {
        return accountEmailReceiver;
    }

    public void setAccountEmailReceiver(String accountEmailReceiver) {
        this.accountEmailReceiver = accountEmailReceiver;
    }
}
