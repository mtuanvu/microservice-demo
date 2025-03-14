package com.fai.semfour.userservice.services;

import com.fai.semfour.userservice.dto.request.UserRequest;
import com.fai.semfour.userservice.dto.response.UserResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;

public interface UserService {
    UserResponse updateUser(String id, UserRequest request);
    UserResponse getUser(String id);
    PagingResponse<UserResponse> getAllUsers(int pageNumber, int pageSize);
}
