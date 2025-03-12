package com.fai.semfour.userservice.dto.response;

import com.fai.semfour.userservice.entities.VerificationCode.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class VerificationCodeResponse {
    String id;
    String code;
    Type type;
    Timestamp expiryTime;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
