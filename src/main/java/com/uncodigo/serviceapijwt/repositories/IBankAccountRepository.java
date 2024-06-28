package com.uncodigo.serviceapijwt.repositories;

import com.uncodigo.serviceapijwt.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBankAccountRepository extends JpaRepository<BankAccount, Integer> {
    Optional<BankAccount> findByOwner_Id(Integer id);
    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
