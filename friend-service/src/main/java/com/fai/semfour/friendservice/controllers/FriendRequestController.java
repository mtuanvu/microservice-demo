package com.fai.semfour.friendservice.controllers;

import com.fai.semfour.friendservice.dto.request.FriendRequestRequest;
import com.fai.semfour.friendservice.dto.response.FriendRequestResponse;
import com.fai.semfour.friendservice.services.FriendRequestService;
import com.fai.semfour.friendservice.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend-requests")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendRequestController {
    FriendRequestService friendRequestService;

    @PostMapping
    public ResponseEntity<ApiResponse<FriendRequestResponse>> sendFriendRequest(@RequestBody FriendRequestRequest request) {
        FriendRequestResponse friendRequest = friendRequestService.sendFriendRequest(request);
        return ResponseEntity.ok(ApiResponse.<FriendRequestResponse>builder()
                .code(200)
                .data(friendRequest)
                .build());
    }

    @PostMapping("/{requestId}/accept")
    public ResponseEntity<ApiResponse<String>> acceptFriendRequest(@PathVariable String requestId) {
        friendRequestService.acceptFriendRequest(requestId);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .code(200)
                .data("Friend request accepted.")
                .build());
    }

    @PostMapping("/{requestId}/reject")
    public ResponseEntity<ApiResponse<String>> rejectFriendRequest(@PathVariable String requestId) {
        friendRequestService.rejectFriendRequest(requestId);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .code(200)
                .data("Friend request rejected.")
                .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<FriendRequestResponse>>> getPendingRequests(@PathVariable String userId) {
        List<FriendRequestResponse> pendingRequests = friendRequestService.getPendingRequests(userId);
        return ResponseEntity.ok(ApiResponse.<List<FriendRequestResponse>>builder()
                .code(200)
                .data(pendingRequests)
                .build());
    }
}
