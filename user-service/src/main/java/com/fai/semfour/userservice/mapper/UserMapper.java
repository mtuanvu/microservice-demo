package com.fai.semfour.userservice.mapper;

import com.fai.semfour.userservice.dto.request.AccountRequest;
import com.fai.semfour.userservice.dto.request.UserRequest;
import com.fai.semfour.userservice.dto.response.UserResponse;
import com.fai.semfour.userservice.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(AccountRequest request, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserRequest request);

    UserResponse toUserResponse(User user);
}
