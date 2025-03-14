package com.fai.semfour.userservice.controllers;

import com.fai.semfour.userservice.dto.request.UserRequest;
import com.fai.semfour.userservice.dto.response.UserResponse;
import com.fai.semfour.userservice.services.UserService;
import com.fai.semfour.userservice.utils.ApiResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    public ApiResponse<PagingResponse<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ApiResponse.<PagingResponse<UserResponse>>builder()
                .code(200)
                .data(userService.getAllUsers(pageNumber, pageSize))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .data(userService.updateUser(id, request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .data(userService.getUser(id))
                .build();
    }
}
