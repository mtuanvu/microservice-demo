package com.fai.semfour.userservice.controllers;


import com.fai.semfour.userservice.dto.request.AccessKeyRequest;
import com.fai.semfour.userservice.dto.response.AccessKeyResponse;
import com.fai.semfour.userservice.services.AccessKeyService;
import com.fai.semfour.userservice.utils.ApiResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/access-keys")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccessKeyController {
    AccessKeyService accessKeyService;

    @PostMapping
    public ApiResponse<AccessKeyResponse> createAccessKey(@RequestBody AccessKeyRequest request) {
        return ApiResponse.<AccessKeyResponse>builder()
                .code(200)
                .data(accessKeyService.createAccessKey(request))
                .build();
    }

    @GetMapping
    public ApiResponse<PagingResponse<AccessKeyResponse>> getAllAccessKeys(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ApiResponse.<PagingResponse<AccessKeyResponse>>builder()
                .code(200)
                .data(accessKeyService.getAllAccessKeys(pageNumber, pageSize))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<AccessKeyResponse> updateAccessKey(@PathVariable String id, @RequestBody AccessKeyRequest request) {
        return ApiResponse.<AccessKeyResponse>builder()
                .code(200)
                .data(accessKeyService.updateAccessKey(id, request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<AccessKeyResponse> getAccessKey(@PathVariable String id) {
        return ApiResponse.<AccessKeyResponse>builder()
                .code(200)
                .data(accessKeyService.getAccessKey(id))
                .build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAccessKey(@PathVariable String id) {
        accessKeyService.deleteAccessKey(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(200)
                .error("Deleted!")
                .build();


        return ResponseEntity.ok(apiResponse);
    }
}
