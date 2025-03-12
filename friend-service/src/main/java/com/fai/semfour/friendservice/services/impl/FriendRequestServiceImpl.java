package com.fai.semfour.friendservice.services.impl;

import com.fai.semfour.friendservice.dto.request.FriendRequestRequest;
import com.fai.semfour.friendservice.dto.response.FriendRequestResponse;
import com.fai.semfour.friendservice.entities.Friend;
import com.fai.semfour.friendservice.entities.FriendRequest;
import com.fai.semfour.friendservice.entities.FriendRequest.Status;
import com.fai.semfour.friendservice.exception.AppException;
import com.fai.semfour.friendservice.exception.ErrorCode;
import com.fai.semfour.friendservice.mapper.FriendRequestMapper;
import com.fai.semfour.friendservice.repositories.FriendRepository;
import com.fai.semfour.friendservice.repositories.FriendRequestRepository;
import com.fai.semfour.friendservice.services.FriendRequestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendRequestServiceImpl implements FriendRequestService {
    FriendRequestRepository friendRequestRepository;
    FriendRepository friendRepository;
    FriendRequestMapper friendRequestMapper;

    @Override
    @Transactional
    public FriendRequestResponse sendFriendRequest(FriendRequestRequest request) {
        if (friendRequestRepository.existsBySenderIdAndReceiverId(request.getSenderId(), request.getReceiverId())) {
            throw new AppException(ErrorCode.FRIEND_REQUEST_ALREADY_SENT);
        }

        FriendRequest friendRequest = friendRequestMapper.toFriendRequest(request);
        friendRequest.setStatus(Status.PENDING);
        friendRequest = friendRequestRepository.save(friendRequest);

        return friendRequestMapper.toFriendRequestResponse(friendRequest);
    }

    @Override
    @Transactional
    public void acceptFriendRequest(String requestId) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(ErrorCode.FRIEND_REQUEST_NOT_FOUND));

        friendRequest.setStatus(Status.ACCEPTED);
        friendRequestRepository.save(friendRequest);

        Friend friend = new Friend(friendRequest.getSenderId(), friendRequest.getReceiverId());
        friendRepository.save(friend);
    }

    @Override
    @Transactional
    public void rejectFriendRequest(String requestId) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(ErrorCode.FRIEND_REQUEST_NOT_FOUND));

        friendRequest.setStatus(Status.REJECTED);
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public List<FriendRequestResponse> getPendingRequests(String userId) {
        return friendRequestRepository.findByReceiverIdAndStatus(userId, Status.PENDING)
                .stream()
                .map(friendRequestMapper::toFriendRequestResponse)
                .toList();
    }
}
