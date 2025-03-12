package com.fai.semfour.userservice.mapper;

import com.fai.semfour.userservice.dto.request.PermissionRequest;
import com.fai.semfour.userservice.dto.response.PermissionResponse;
import com.fai.semfour.userservice.entities.Permission;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
