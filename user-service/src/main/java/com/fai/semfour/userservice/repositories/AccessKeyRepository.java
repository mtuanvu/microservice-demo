package com.fai.semfour.userservice.repositories;

import com.fai.semfour.userservice.entities.AccessKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessKeyRepository extends JpaRepository<AccessKey, String> {
    @Query("SELECT a FROM AccessKey a WHERE a.id = :id")
    Optional<AccessKey> findById(@Param("id") String id);

    @Query("SELECT a FROM AccessKey a")
    Page<AccessKey> findAllAccessKey(Pageable pageable);
}
