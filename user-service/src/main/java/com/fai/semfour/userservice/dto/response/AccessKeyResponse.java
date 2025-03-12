package com.fai.semfour.userservice.dto.response;

import com.fai.semfour.userservice.entities.AccessKey.DeviceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AccessKeyResponse {
    String id;
    String accessToken;
    String refreshToken;
    String deviceId;
    DeviceType deviceType;
    LocalDateTime lastUsed;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
