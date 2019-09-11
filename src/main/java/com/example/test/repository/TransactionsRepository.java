package com.example.test.repository;

import com.example.test.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT t FROM  Transaction t JOIN t.code WHERE code = ?1")
    List<Transaction> findByCode (int code);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transactions (code_id, status, time, contact_number) VALUES (?1,?2,?3,?4)", nativeQuery = true)
    void saveWithCode (long codeId, String status, Timestamp time, int cNum);
}
