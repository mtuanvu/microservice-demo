package com.fai.semfour.friendservice.repositories;

import com.fai.semfour.friendservice.entities.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockedUserRepository extends JpaRepository<BlockedUser, String> {
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BlockedUser b WHERE b.userId = :userId AND b.blockedUserId = :blockedUserId")
    boolean existsByUserIdAndBlockedUserId(@Param("userId") String userId, @Param("blockedUserId") String blockedUserId);

    @Query("SELECT b FROM BlockedUser b WHERE b.userId = :userId AND b.blockedUserId = :blockedUserId")
    Optional<BlockedUser> findByUserIdAndBlockedUserId(@Param("userId") String userId, @Param("blockedUserId") String blockedUserId);

    @Query("SELECT b FROM BlockedUser b WHERE b.userId = :userId")
    List<BlockedUser> findByUserId(@Param("userId") String userId);

}
