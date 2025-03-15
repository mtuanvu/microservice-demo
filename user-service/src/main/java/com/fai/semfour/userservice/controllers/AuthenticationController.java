package com.fai.semfour.userservice.controllers;

import com.fai.semfour.userservice.dto.request.*;
import com.fai.semfour.userservice.dto.response.AccessKeyResponse;
import com.fai.semfour.userservice.dto.response.IntrospectResponse;
import com.fai.semfour.userservice.dto.response.RefreshTokenResponse;
import com.fai.semfour.userservice.services.impl.AuthenticationService;
import com.fai.semfour.userservice.utils.ApiResponse;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AccessKeyResponse> login(@RequestBody AuthenticationRequest request) {
        return ApiResponse.<AccessKeyResponse>builder()
                .code(200)
                .data(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) throws ParseException, JOSEException {
        return ApiResponse.<RefreshTokenResponse>builder()
                .code(200)
                .data(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .code(200)
                .data(authenticationService.introspect(request))
                .build();
    }
}
