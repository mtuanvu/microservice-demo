package com.fai.semfour.userservice.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static org.springframework.http.HttpStatus.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", INTERNAL_SERVER_ERROR),
    INVALID_KEY(1111, "Invalid message key", BAD_REQUEST),

    INVALID_INPUT(1001, "Invalid input data", BAD_REQUEST),
    MISSING_REQUIRED_FIELD(1002, "Missing required field", BAD_REQUEST),
    ROLE_ALREADY_EXISTS(1007, "Role already exists", BAD_REQUEST),

    UNAUTHORIZED(2001, "Unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(2002, "Access denied", HttpStatus.FORBIDDEN),
    INVALID_PERMISSION_IDS(1009, "Permission does not exist", BAD_REQUEST),


    USER_NOT_FOUND(3001, "User not found", NOT_FOUND),
    PERMISSION_NOT_FOUND(3002, "Permission not found", NOT_FOUND),
    ACCESS_KEY_NOT_FOUND(3003, "Access key not found", NOT_FOUND),
    ACCOUNT_NOT_FOUND(3004, "Account not found", NOT_FOUND),
    ROLE_NOT_FOUND(3005, "Role not found", NOT_FOUND),
    VERIFICATION_CODE_NOT_FOUND(3006, "Verification code not found", NOT_FOUND),
    ACCOUNT_BLOCKED(3002, "Account is blocked", HttpStatus.FORBIDDEN),

    INVALID_ACCESS_TOKEN(4001, "Invalid access token", HttpStatus.UNAUTHORIZED),
    EXPIRED_ACCESS_TOKEN(4002, "Expired access token", HttpStatus.UNAUTHORIZED),

    DATABASE_ERROR(5001, "Database error", INTERNAL_SERVER_ERROR);
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatusCode statusCode;


    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
}
