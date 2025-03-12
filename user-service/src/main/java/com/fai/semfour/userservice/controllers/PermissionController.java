package com.fai.semfour.userservice.controllers;

import com.fai.semfour.userservice.dto.request.PermissionRequest;
import com.fai.semfour.userservice.dto.response.PermissionResponse;
import com.fai.semfour.userservice.services.PermissionService;
import com.fai.semfour.userservice.utils.ApiResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
            return ApiResponse.<PermissionResponse>builder()
                    .code(200)
                    .data(permissionService.createPermission(request))
                    .build();
    }

    @GetMapping
    public ApiResponse<PagingResponse<PermissionResponse>> getAllPermissions(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ApiResponse.<PagingResponse<PermissionResponse>>builder()
                .code(200)
                .data(permissionService.getAllPermissions(pageNumber, pageSize))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PermissionResponse> updatePermission(@PathVariable("id") Long id, @RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .data(permissionService.updatePermission(id, request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PermissionResponse> getPermission(@PathVariable("id") Long id) {
        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .data(permissionService.getPermission(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePermission(@PathVariable("id") Long id) {
        permissionService.deletePermission(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(200)
                .error("Deleted!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
