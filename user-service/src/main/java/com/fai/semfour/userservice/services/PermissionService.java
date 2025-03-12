package com.fai.semfour.userservice.services;

import com.fai.semfour.userservice.dto.request.PermissionRequest;
import com.fai.semfour.userservice.dto.response.PermissionResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);
    PagingResponse<PermissionResponse> getAllPermissions(int pageNumber, int pageSize);
    PermissionResponse updatePermission(Long id, PermissionRequest request);
    PermissionResponse getPermission(Long id);
    void deletePermission(Long id);
}
