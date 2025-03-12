package com.fai.semfour.friendservice.services.impl;

import com.fai.semfour.friendservice.dto.request.BlockedUserRequest;
import com.fai.semfour.friendservice.dto.response.BlockedUserResponse;
import com.fai.semfour.friendservice.entities.BlockedUser;
import com.fai.semfour.friendservice.exception.AppException;
import com.fai.semfour.friendservice.exception.ErrorCode;
import com.fai.semfour.friendservice.mapper.BlockedUserMapper;
import com.fai.semfour.friendservice.repositories.BlockedUserRepository;
import com.fai.semfour.friendservice.services.BlockedUserService;
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
public class BlockedUserServiceImpl implements BlockedUserService {
    BlockedUserRepository blockedUserRepository;
    BlockedUserMapper blockedUserMapper;

    @Override
    @Transactional
    public BlockedUserResponse blockUser(BlockedUserRequest request) {
        if (blockedUserRepository.existsByUserIdAndBlockedUserId(request.getUserId(), request.getBlockedUserId())) {
            throw new AppException(ErrorCode.USER_ALREADY_BLOCKED);
        }

        BlockedUser blockedUser = blockedUserMapper.toBlockedUser(request);
        blockedUser = blockedUserRepository.save(blockedUser);

        return blockedUserMapper.toBlockedUserResponse(blockedUser);
    }

    @Override
    @Transactional
    public void unblockUser(String userId, String blockedUserId) {
        BlockedUser blockedUser = blockedUserRepository.findByUserIdAndBlockedUserId(userId, blockedUserId)
                .orElseThrow(() -> new AppException(ErrorCode.BLOCKED_USER_NOT_FOUND));

        blockedUserRepository.delete(blockedUser);
    }

    @Override
    public List<BlockedUserResponse> getBlockedUsers(String userId) {
        return blockedUserRepository.findByUserId(userId)
                .stream()
                .map(blockedUserMapper::toBlockedUserResponse)
                .toList();
    }
}
