package com.fai.semfour.userservice.services;

import com.fai.semfour.userservice.dto.request.RoleRequest;
import com.fai.semfour.userservice.dto.response.RoleResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);
    RoleResponse updateRole(Long id, RoleRequest request);
    RoleResponse getRole(Long id);
    PagingResponse<RoleResponse> getAllRoles(int pageNumber, int pageSize);
    void deleteRole(Long id);
}
