package com.fai.semfour.friendservice.repositories;

import com.fai.semfour.friendservice.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, String> {
    Optional<Friend> findByUserIdAndFriendId(String userId, String friendId);
    boolean existsByUserIdAndFriendId(String userId, String friendId);
    List<Friend> findByUserId(String userId);
    void deleteByUserIdAndFriendId(String userId, String friendId);
}
