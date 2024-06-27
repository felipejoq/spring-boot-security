package com.uncodigo.serviceapijwt.repositories;

import com.uncodigo.serviceapijwt.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
}
