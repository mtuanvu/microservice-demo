package com.fai.semfour.friendservice.mapper;

import com.fai.semfour.friendservice.dto.request.FriendRequestRequest;
import com.fai.semfour.friendservice.dto.response.FriendRequestResponse;
import com.fai.semfour.friendservice.entities.FriendRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FriendRequestMapper {
    FriendRequest toFriendRequest(FriendRequestRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFriendRequest(@MappingTarget FriendRequest friendRequestDto, FriendRequestRequest request);

    FriendRequestResponse toFriendRequestResponse(FriendRequest friendRequest);
}
