package com.fai.semfour.userservice.mapper;

import com.fai.semfour.userservice.dto.request.AccessKeyRequest;
import com.fai.semfour.userservice.dto.response.AccessKeyResponse;
import com.fai.semfour.userservice.entities.AccessKey;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccessKeyMapper {
    AccessKey toAccessKey(AccessKeyRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccessKey(@MappingTarget AccessKey accessKey, AccessKeyRequest request);

    AccessKeyResponse toAccessKeyResponse(AccessKey accessKey);
}
