package com.fai.semfour.userservice.repositories;

import com.fai.semfour.userservice.entities.VerificationCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, String> {

    @Query("SELECT v FROM VerificationCode v WHERE v.id = :id")
    Optional<VerificationCode> findById(@Param("id") String id);

    @Query("SELECT v FROM VerificationCode v")
    Page<VerificationCode> findAllVerifications(Pageable pageable);
}
