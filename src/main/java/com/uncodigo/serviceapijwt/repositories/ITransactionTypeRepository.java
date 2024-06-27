package com.uncodigo.serviceapijwt.repositories;

import com.uncodigo.serviceapijwt.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
}
