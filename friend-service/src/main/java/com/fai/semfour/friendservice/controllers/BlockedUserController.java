package com.fai.semfour.friendservice.controllers;

import com.fai.semfour.friendservice.dto.request.BlockedUserRequest;
import com.fai.semfour.friendservice.dto.response.BlockedUserResponse;
import com.fai.semfour.friendservice.services.BlockedUserService;
import com.fai.semfour.friendservice.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocked-users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlockedUserController {
    BlockedUserService blockedUserService;

    @PostMapping
    public ResponseEntity<ApiResponse<BlockedUserResponse>> blockUser(@RequestBody BlockedUserRequest request) {
        BlockedUserResponse blockedUser = blockedUserService.blockUser(request);
        return ResponseEntity.ok(ApiResponse.<BlockedUserResponse>builder()
                .code(200)
                .data(blockedUser)
                .build());
    }

    @DeleteMapping("/{userId}/{blockedUserId}")
    public ResponseEntity<ApiResponse<String>> unblockUser(@PathVariable String userId, @PathVariable String blockedUserId) {
        blockedUserService.unblockUser(userId, blockedUserId);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .code(200)
                .data("User unblocked successfully.")
                .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<BlockedUserResponse>>> getBlockedUsers(@PathVariable String userId) {
        List<BlockedUserResponse> blockedUsers = blockedUserService.getBlockedUsers(userId);
        return ResponseEntity.ok(ApiResponse.<List<BlockedUserResponse>>builder()
                .code(200)
                .data(blockedUsers)
                .build());
    }
}
