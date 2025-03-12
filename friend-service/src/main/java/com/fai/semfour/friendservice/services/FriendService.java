package com.fai.semfour.friendservice.services;

import com.fai.semfour.friendservice.entities.Friend;

import java.util.List;

public interface FriendService {
    void addFriend(String userId, String friendId);
    void removeFriend(String userId, String friendId);
    boolean areFriends(String userId, String friendId);
    List<Friend> getFriends(String userId);
}
