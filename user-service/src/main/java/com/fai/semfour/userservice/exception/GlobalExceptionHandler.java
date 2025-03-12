package com.fai.semfour.userservice.exception;

import com.fai.semfour.userservice.utils.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //AppException xu ly loi do logic
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException e) {
//        log.error("AppException: {}", e.getErrorCode().getMessage(), e);
        return buildResponse(e.getErrorCode());
    }

    //xu ly loi MethodArgumentNotValidException validatin tu dto
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException e) {
        String errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.<String>builder()
                        .code(1001)
                        .error("Invalid input: " + errors)
                        .build());
    }


    //ConstraintViolationException xu ly loi validation cap database hoac @Valid
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return buildResponse(ErrorCode.INVALID_INPUT, e.getMessage());
    }

    //xu ly loi AccessDeniedException do nguoi dung khong co quyen
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e) {
        return buildResponse(ErrorCode.FORBIDDEN);
    }

    //xu ly loi IllegalArgumentException do tham so sai
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return buildResponse(ErrorCode.INVALID_INPUT, e.getMessage());
    }

    //xu ly loi NullPointerException xay ra do null
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException e) {
        return buildResponse(ErrorCode.UNCATEGORIZED_EXCEPTION, "Null Pointer Exception occurred.");
    }

    //Xu ly loi 'Exception' chua duoc dinh nghia
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.<String>builder()
                        .code(5000)
                        .error("Unexpected error: " + e.getMessage())
                        .build());
    }

    //helper method: xay dung response tu 'ErrorCode'
    private ResponseEntity<ApiResponse> buildResponse(ErrorCode errorCode) {
        return buildResponse(errorCode, errorCode.getMessage());
    }

    private ResponseEntity<ApiResponse> buildResponse(ErrorCode errorCode, String customMessage) {
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(errorCode.getCode())
                .error(customMessage)
                .build();
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }
}
