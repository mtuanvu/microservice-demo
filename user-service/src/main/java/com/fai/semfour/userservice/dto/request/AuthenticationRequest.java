package com.fai.semfour.userservice.dto.request;

import com.fai.semfour.userservice.entities.AccessKey.DeviceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AuthenticationRequest {
    String username;
    String password;
    String deviceId;
    DeviceType deviceType;
}
