package com.fai.semfour.userservice.dto.request;

import com.fai.semfour.userservice.entities.AccessKey.DeviceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccessKeyRequest {
    String accessToken;
    String refreshToken;
    String deviceId;
    DeviceType deviceType;
    String userId;
}
