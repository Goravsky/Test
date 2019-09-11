package com.example.test.repository;

import com.example.test.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodeRepository extends JpaRepository<Code,Long> {
    @Query("SELECT c FROM Code c WHERE c.code = ?1")
    Code findByCode(int code);
}
