package com.fai.semfour.userservice.controllers;

import com.fai.semfour.userservice.dto.request.AccountRequest;
import com.fai.semfour.userservice.dto.response.AccountResponse;
import com.fai.semfour.userservice.services.AccountService;
import com.fai.semfour.userservice.utils.ApiResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @PostMapping
    public ApiResponse<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .code(200)
                .data(accountService.createAccount(request))
                .build();
    }

    @GetMapping
    public ApiResponse<PagingResponse<AccountResponse>> getAllAccounts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ApiResponse.<PagingResponse<AccountResponse>>builder()
                .code(200)
                .data(accountService.getAllAccounts(pageNumber, pageSize))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<AccountResponse> updateAccount(@PathVariable String id, @RequestBody AccountRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .code(200)
                .data(accountService.updateAccount(id, request))
                .build();
    }


    @GetMapping("/{id}")
    public ApiResponse<AccountResponse> getAccount(@PathVariable String id) {
        return ApiResponse.<AccountResponse>builder()
                .code(200)
                .data(accountService.getAccount(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(200)
                .error("Deleted!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
