package com.fai.semfour.userservice.mapper;

import com.fai.semfour.userservice.dto.request.AuthenticationRequest;
import com.fai.semfour.userservice.dto.response.AccessKeyResponse;
import com.fai.semfour.userservice.entities.AccessKey;
import com.fai.semfour.userservice.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, LocalDateTime.class})
public interface AccessKeyMapper {
    @Mapping(source = "accessToken", target = "accessToken")
    @Mapping(source = "refreshToken", target = "refreshToken")
    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "deviceType", target = "deviceType")
    @Mapping(source = "isActive", target = "isActive")
    AccessKeyResponse toResponse(AccessKey accessKey);

    @Mapping(target = "isActive", expression = "java(Boolean.TRUE)")
    @Mapping(target = "account", ignore = true)
    AccessKey toAccessKey(AuthenticationRequest request, String accessToken, String refreshToken);
}
