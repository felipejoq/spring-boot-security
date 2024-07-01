package com.uncodigo.serviceapijwt.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncodigo.serviceapijwt.models.Transaction;
import com.uncodigo.serviceapijwt.models.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDto {
    private Integer id;
    private BigDecimal total;
    @JsonProperty("transaction_date")
    private Date transactionDate;
    private String sender;
    private String receiver;
    @JsonProperty("transaction_type")
    private TransactionType transactionType;

    public TransactionDto() {
    }

    public TransactionDto(Integer id, BigDecimal total, Date transactionDate, String sender, String receiver, TransactionType transactionType) {
        this.id = id;
        this.total = total;
        this.transactionDate = transactionDate;
        this.sender = sender;
        this.receiver = receiver;
        this.transactionType = transactionType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public static TransactionDto fromTransaction(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setTotal(transaction.getTotal());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setSender(transaction.getSender().getOwner().getEmail());
        dto.setReceiver(transaction.getReceiver() != null ? transaction.getReceiver().getOwner().getEmail() : null);
        dto.setTransactionType(transaction.getTransactionType());
        return dto;
    }
}
