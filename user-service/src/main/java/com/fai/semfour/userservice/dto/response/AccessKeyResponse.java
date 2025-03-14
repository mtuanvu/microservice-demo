package com.fai.semfour.userservice.dto.response;

import com.fai.semfour.userservice.entities.AccessKey.DeviceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AccessKeyResponse {
    String accessToken;
    String refreshToken;
    String deviceId;
    DeviceType deviceType;
    Boolean isActive;
}
