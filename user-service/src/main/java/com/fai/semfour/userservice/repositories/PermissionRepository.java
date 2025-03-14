package com.fai.semfour.userservice.repositories;

import com.fai.semfour.userservice.entities.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("SELECT p FROM Permission p")
    Page<Permission> findAllPermission(Pageable pageable);

    @Query("SELECT p FROM Permission p WHERE p.id = :id")
    Optional<Permission> findById(@Param("id") Long id);

    @Query("SELECT p FROM Permission p WHERE p.id IN :ids")
    List<Permission> findAllByIdPermissions(@Param("ids") Set<Long> ids);
}
