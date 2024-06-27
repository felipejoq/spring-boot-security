package com.uncodigo.serviceapijwt.repositories;

import com.uncodigo.serviceapijwt.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
