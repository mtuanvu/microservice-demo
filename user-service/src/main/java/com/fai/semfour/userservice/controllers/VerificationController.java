package com.fai.semfour.userservice.controllers;

import com.fai.semfour.userservice.dto.request.VerificationCodeRequest;
import com.fai.semfour.userservice.dto.response.VerificationCodeResponse;
import com.fai.semfour.userservice.services.VerificationService;
import com.fai.semfour.userservice.utils.ApiResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verifications")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationController {
    VerificationService verificationService;

    @PostMapping
    public ApiResponse<VerificationCodeResponse> createVerification(@RequestBody VerificationCodeRequest request) {
        return ApiResponse.<VerificationCodeResponse>builder()
                .code(200)
                .data(verificationService.createVerification(request))
                .build();
    }

    @GetMapping
    public ApiResponse<PagingResponse<VerificationCodeResponse>> getAllVerifications(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ApiResponse.<PagingResponse<VerificationCodeResponse>>builder()
                .code(200)
                .data(verificationService.getAllVerifications(pageNumber, pageSize))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<VerificationCodeResponse> updateVerification(@PathVariable String id, @RequestBody VerificationCodeRequest request) {
        return ApiResponse.<VerificationCodeResponse>builder()
                .code(200)
                .data(verificationService.updateVerification(id, request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<VerificationCodeResponse> getVerification(@PathVariable String id) {
        return ApiResponse.<VerificationCodeResponse>builder()
                .code(200)
                .data(verificationService.getVerification(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteVerification(@PathVariable String id) {
        verificationService.deleteVerification(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(200)
                .error("Deleted!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
