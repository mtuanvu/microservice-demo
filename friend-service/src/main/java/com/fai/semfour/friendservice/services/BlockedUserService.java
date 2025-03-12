package com.fai.semfour.friendservice.services;

import com.fai.semfour.friendservice.dto.request.BlockedUserRequest;
import com.fai.semfour.friendservice.dto.response.BlockedUserResponse;

import java.util.List;

public interface BlockedUserService {
    BlockedUserResponse blockUser(BlockedUserRequest request);
    void unblockUser(String userId, String blockedUserId);
    List<BlockedUserResponse> getBlockedUsers(String userId);
}
