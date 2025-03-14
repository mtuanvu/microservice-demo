package com.fai.semfour.userservice.repositories;

import com.fai.semfour.userservice.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findById(@Param("id") String id);

    @Query("SELECT a FROM Account a")
    Page<Account> findAllAccount(Pageable pageable);

    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.username = :username")
    boolean existsByUsername(@Param("username") String username);
}
