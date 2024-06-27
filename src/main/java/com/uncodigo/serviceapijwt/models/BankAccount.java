package com.uncodigo.serviceapijwt.models;

import com.fasterxml.jackson.annotation.*;
import com.uncodigo.serviceapijwt.utils.UniqueNumberGenerator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;

@Entity
@Table(name = "bank_accounts")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User owner;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "sender")
    private Collection<Transaction> transaction;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, BigDecimal balance, User owner, Currency currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.currency = currency;
    }

    @PrePersist
    public void prePersist() {
        // Add unique number to account
        UniqueNumberGenerator generator = new UniqueNumberGenerator();
        this.accountNumber = String.valueOf(generator.generateUniqueNumber());
        // Set balance to 0
        this.balance = BigDecimal.ZERO;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Collection<Transaction> getTransaction() {
        // Sort by date
        return transaction.stream().sorted(Comparator.comparing(Transaction::getTransactionDate).reversed()).toList();
    }

    public void setTransaction(Collection<Transaction> transaction) {
        this.transaction = transaction;
    }
}
