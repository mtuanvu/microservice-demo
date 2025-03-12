package com.fai.semfour.friendservice.services;

import com.fai.semfour.friendservice.dto.request.FriendRequestRequest;
import com.fai.semfour.friendservice.dto.response.FriendRequestResponse;

import java.util.List;

public interface FriendRequestService {
    FriendRequestResponse sendFriendRequest(FriendRequestRequest request);
    void acceptFriendRequest(String requestId);
    void rejectFriendRequest(String requestId);
    List<FriendRequestResponse> getPendingRequests(String userId);
}
