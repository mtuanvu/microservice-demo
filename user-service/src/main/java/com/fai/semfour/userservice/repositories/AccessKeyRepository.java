package com.fai.semfour.userservice.repositories;

import com.fai.semfour.userservice.entities.AccessKey;
import com.fai.semfour.userservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessKeyRepository extends JpaRepository<AccessKey, String> {
    @Query("SELECT a FROM AccessKey a WHERE a.id = :id")
    Optional<AccessKey> findById(@Param("id") String id);

    @Query("SELECT a FROM AccessKey a WHERE a.account = :account")
    List<AccessKey> findByAccount(@Param("account") Account account);

    @Query("SELECT a FROM AccessKey a WHERE a.deviceId = :deviceId AND a.isActive = true")
    Optional<AccessKey> findByDeviceIdAndIsActiveTrue(@Param("deviceId") String deviceId);

    @Query("SELECT a FROM AccessKey a WHERE a.refreshToken = :refreshToken AND a.isActive = true")
    Optional<AccessKey> findByRefreshTokenAndIsActiveTrue(@Param("refreshToken") String refreshToken);

    @Query("SELECT a FROM AccessKey a WHERE a.accessToken = :accessToken AND a.isActive = true")
    Optional<AccessKey> findByAccessTokenAndIsActiveTrue(@Param("accessToken") String accessToken);

}
