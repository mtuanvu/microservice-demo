package com.fai.semfour.userservice.mapper;

import com.fai.semfour.userservice.dto.request.RoleRequest;
import com.fai.semfour.userservice.dto.response.RoleResponse;
import com.fai.semfour.userservice.entities.Role;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRole(@MappingTarget Role role, RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
