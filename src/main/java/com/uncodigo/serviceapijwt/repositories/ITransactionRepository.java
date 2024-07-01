package com.uncodigo.serviceapijwt.repositories;

import com.uncodigo.serviceapijwt.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.sender.id = :accountId OR t.receiver.id = :accountId ORDER BY t.transactionDate DESC")
    Page<Transaction> findTransactionsByAccountId(@Param("accountId") Integer accountId, Pageable pageable);
}
