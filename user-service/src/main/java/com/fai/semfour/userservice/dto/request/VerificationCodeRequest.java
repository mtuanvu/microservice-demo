package com.fai.semfour.userservice.dto.request;

import com.fai.semfour.userservice.entities.VerificationCode.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerificationCodeRequest {
    String code;
    Type type;
    Timestamp expiryTime;
}
