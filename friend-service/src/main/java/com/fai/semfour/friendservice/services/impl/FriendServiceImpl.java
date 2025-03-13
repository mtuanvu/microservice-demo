package com.fai.semfour.friendservice.services.impl;

import com.fai.semfour.friendservice.entities.Friend;
import com.fai.semfour.friendservice.repositories.FriendRepository;
import com.fai.semfour.friendservice.services.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    @Override
    @Transactional
    public void addFriend(String userId, String friendId) {
        if (userId.equals(friendId)) {
            throw new IllegalArgumentException("Cannot add yourself as a friend.");
        }

        Optional<Friend> existingFriendship = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (existingFriendship.isPresent()) {
            log.warn("Friendship already exists between userId={} and friendId={}", userId, friendId);
            return;
        }

        Friend friend1 = new Friend(userId, friendId);
        Friend friend2 = new Friend(friendId, userId);
        friendRepository.save(friend1);
        friendRepository.save(friend2);

        log.info("Friendship created: userId={} friendId={}", userId, friendId);
    }

    @Override
    @Transactional
    public void removeFriend(String userId, String friendId) {
        Optional<Friend> existingFriendship = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (existingFriendship.isEmpty()) {
            log.warn("Friendship does not exist between userId={} and friendId={}", userId, friendId);
            return;
        }

        friendRepository.deleteByUserIdAndFriendId(userId, friendId);
        friendRepository.deleteByUserIdAndFriendId(friendId, userId);

        log.info("Friendship removed: userId={} friendId={}", userId, friendId);
    }

    @Override
    public boolean areFriends(String userId, String friendId) {
        return friendRepository.existsByUserIdAndFriendId(userId, friendId);
    }

    @Override
    public List<Friend> getFriends(String userId) {
        return friendRepository.findByUserId(userId);
    }
}
