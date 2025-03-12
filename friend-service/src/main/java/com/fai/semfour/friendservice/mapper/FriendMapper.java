package com.fai.semfour.friendservice.mapper;

import com.fai.semfour.friendservice.dto.request.FriendRequestDto;
import com.fai.semfour.friendservice.dto.response.FriendResponse;
import com.fai.semfour.friendservice.entities.Friend;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FriendMapper {
    Friend toFriend(FriendRequestDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFriend(@MappingTarget Friend friend, FriendRequestDto request);

    FriendResponse toFriendResponse(Friend friend);
}
