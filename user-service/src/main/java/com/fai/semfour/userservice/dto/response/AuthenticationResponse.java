package com.fai.semfour.userservice.dto.response;

import com.fai.semfour.userservice.entities.AccessKey.DeviceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String accessToken;
    String refreshToken;
    String deviceId;
    DeviceType deviceType;
    boolean authenticated;
}
