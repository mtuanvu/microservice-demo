package com.fai.semfour.friendservice.controllers;

import com.fai.semfour.friendservice.dto.request.FriendRequestDto;
import com.fai.semfour.friendservice.dto.response.FriendResponse;
import com.fai.semfour.friendservice.services.FriendService;
import com.fai.semfour.friendservice.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/friends")
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendController {
//    FriendService friendService;
//
//    @PostMapping
//    public ResponseEntity<ApiResponse<FriendResponse>> addFriend(@RequestBody FriendRequestDto request) {
//        FriendResponse friend = friendService.addFriend(request);
//        return ResponseEntity.ok(ApiResponse.<FriendResponse>builder()
//                .code(200)
//                .data(friend)
//                .build());
//    }
//
//    @DeleteMapping("/{userId}/{friendId}")
//    public ResponseEntity<ApiResponse<String>> removeFriend(@PathVariable String userId, @PathVariable String friendId) {
//        friendService.removeFriend(userId, friendId);
//        return ResponseEntity.ok(ApiResponse.<String>builder()
//                .code(200)
//                .data("Friend removed successfully.")
//                .build());
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<ApiResponse<List<FriendResponse>>> getFriends(@PathVariable String userId) {
//        List<FriendResponse> friends = friendService.getFriends(userId);
//        return ResponseEntity.ok(ApiResponse.<List<FriendResponse>>builder()
//                .code(200)
//                .data(friends)
//                .build());
//    }
}
