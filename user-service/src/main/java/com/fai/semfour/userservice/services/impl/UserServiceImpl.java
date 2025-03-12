package com.fai.semfour.userservice.services.impl;

import com.fai.semfour.userservice.dto.FriendEvent;
import com.fai.semfour.userservice.dto.request.UserRequest;
import com.fai.semfour.userservice.dto.response.UserResponse;
import com.fai.semfour.userservice.entities.Account;
import com.fai.semfour.userservice.entities.User;
import com.fai.semfour.userservice.exception.AppException;
import com.fai.semfour.userservice.exception.ErrorCode;
import com.fai.semfour.userservice.kafka.FriendEventProducer;
import com.fai.semfour.userservice.mapper.UserMapper;
import com.fai.semfour.userservice.repositories.AccountRepository;
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
    AccountRepository accountRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        if (request.getAccountId() == null || request.getAccountId().isEmpty()) {
            throw new IllegalArgumentException("Account ID không được để trống");
        }

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account không tồn tại"));

        User user = userMapper.toUser(request);
        user.setAccount(account);

        return userMapper.toUserResponse(userRepository.save(user));
    }

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

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );

        userRepository.delete(user);
    }
}
