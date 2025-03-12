package com.fai.semfour.friendservice.mapper;

import com.fai.semfour.friendservice.dto.request.BlockedUserRequest;
import com.fai.semfour.friendservice.dto.response.BlockedUserResponse;
import com.fai.semfour.friendservice.entities.BlockedUser;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BlockedUserMapper {
    BlockedUser toBlockedUser(BlockedUserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBlockedUser(@MappingTarget BlockedUser blockedUser, BlockedUserRequest request);

    BlockedUserResponse toBlockedUserResponse(BlockedUser blockedUser);
}
