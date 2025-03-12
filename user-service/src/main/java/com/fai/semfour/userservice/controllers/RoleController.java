package com.fai.semfour.userservice.controllers;

import com.fai.semfour.userservice.dto.request.RoleRequest;
import com.fai.semfour.userservice.dto.response.RoleResponse;
import com.fai.semfour.userservice.services.RoleService;
import com.fai.semfour.userservice.utils.ApiResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .data(roleService.createRole(request))
                .build();
    }

    @GetMapping
    public ApiResponse<PagingResponse<RoleResponse>> getAllRoles(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ApiResponse.<PagingResponse<RoleResponse>>builder()
                .code(200)
                .data(roleService.getAllRoles(pageNumber, pageSize))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<RoleResponse> updateRole(@PathVariable Long id, @RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .data(roleService.updateRole(id, request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<RoleResponse> getRole(@PathVariable Long id) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .data(roleService.getRole(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(200)
                .error("Deleted!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
