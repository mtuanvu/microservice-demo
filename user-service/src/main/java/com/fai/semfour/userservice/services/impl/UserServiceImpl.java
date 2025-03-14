package com.fai.semfour.userservice.services.impl;

import com.fai.semfour.userservice.dto.request.UserRequest;
import com.fai.semfour.userservice.dto.response.UserResponse;
import com.fai.semfour.userservice.entities.User;
import com.fai.semfour.userservice.exception.AppException;
import com.fai.semfour.userservice.exception.ErrorCode;
import com.fai.semfour.userservice.mapper.UserMapper;
import com.fai.semfour.userservice.repositories.UserRepository;
import com.fai.semfour.userservice.services.UserService;
import com.fai.semfour.userservice.utils.paging.PageableData;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserResponse updateUser(String id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );

        return userMapper.toUserResponse(user);
    }

    @Override
    public PagingResponse<UserResponse> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> users = userRepository.findAll(pageable);

        List<UserResponse> userResponses = users.getContent()
                .stream().map(userMapper::toUserResponse).toList();

        PageableData pageableData = new PageableData()
                .setPageNumber(users.getNumber())
                .setPageSize(users.getSize())
                .setTotalPages(users.getTotalPages())
                .setTotalRecords(users.getTotalElements());

        return new PagingResponse<UserResponse>()
                .setContents(userResponses)
                .setPaging(pageableData);
    }
}
