package com.fai.semfour.userservice.services.impl;

import com.fai.semfour.userservice.dto.request.RoleRequest;
import com.fai.semfour.userservice.dto.response.RoleResponse;
import com.fai.semfour.userservice.entities.Permission;
import com.fai.semfour.userservice.entities.Role;
import com.fai.semfour.userservice.exception.AppException;
import com.fai.semfour.userservice.exception.ErrorCode;
import com.fai.semfour.userservice.mapper.RoleMapper;
import com.fai.semfour.userservice.repositories.PermissionRepository;
import com.fai.semfour.userservice.repositories.RoleRepository;
import com.fai.semfour.userservice.services.RoleService;
import com.fai.semfour.userservice.utils.paging.PageableData;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Set<Long> ids = request.getPermissions();
        List<Permission> permissions = permissionRepository.findAllByIdPermissions(ids);

        boolean exists = roleRepository.existsByNameAndPermissions(request.getName(), ids);
        if (exists) {
            throw new AppException(ErrorCode.ROLE_ALREADY_EXISTS);
        }


        Role role = roleMapper.toRole(request);
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest request) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        roleMapper.updateRole(role, request);

        Set<Long> ids =request.getPermissions();
        List<Permission> permissions = permissionRepository.findAllByIdPermissions(ids);

        if (permissions.size() != ids.size()) {
            throw new AppException(ErrorCode.INVALID_PERMISSION_IDS);
        }

        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse getRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ROLE_NOT_FOUND)
        );

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public PagingResponse<RoleResponse> getAllRoles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Role> roles = roleRepository.findAll(pageable);

        List<RoleResponse> roleResponses = roles.getContent()
                .stream().map(roleMapper::toRoleResponse).toList();

        PageableData pageableData = new PageableData()
                .setPageNumber(roles.getNumber())
                .setPageSize(roles.getTotalPages())
                .setTotalPages(roles.getTotalPages())
                .setTotalRecords(roles.getTotalElements());

        return new PagingResponse<RoleResponse>()
                .setContents(roleResponses)
                .setPaging(pageableData);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ROLE_NOT_FOUND)
        );

        roleRepository.delete(role);
    }
}
