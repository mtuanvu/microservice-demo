package com.fai.semfour.userservice.services.impl;

import com.fai.semfour.userservice.dto.request.PermissionRequest;
import com.fai.semfour.userservice.dto.response.PermissionResponse;
import com.fai.semfour.userservice.entities.Permission;
import com.fai.semfour.userservice.exception.AppException;
import com.fai.semfour.userservice.exception.ErrorCode;
import com.fai.semfour.userservice.mapper.PermissionMapper;
import com.fai.semfour.userservice.repositories.PermissionRepository;
import com.fai.semfour.userservice.services.PermissionService;
import com.fai.semfour.userservice.utils.paging.PageableData;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public PagingResponse<PermissionResponse> getAllPermissions(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Permission> permissions = permissionRepository.findAllPermission(pageable);

        List<PermissionResponse> permissionResponses = permissions.getContent()
                .stream().map(permissionMapper::toPermissionResponse).toList();

        PageableData pageableData = new PageableData()
                .setPageNumber(permissions.getNumber())
                .setPageSize(permissions.getSize())
                .setTotalPages(permissions.getTotalPages())
                .setTotalRecords(permissions.getTotalElements());

        return new PagingResponse<PermissionResponse>()
                .setContents(permissionResponses)
                .setPaging(pageableData);
    }

    @Override
    public PermissionResponse updatePermission(Long id, PermissionRequest request) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.PERMISSION_NOT_FOUND)
        );

        permissionMapper.updatePermission(permission, request);

        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public PermissionResponse getPermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.PERMISSION_NOT_FOUND)
        );

        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.PERMISSION_NOT_FOUND)
        );

        permissionRepository.delete(permission);
    }
}
