package com.fai.semfour.userservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AccountResponse {
    String id;
    String username;
    List<RoleResponse> roles;
    UserResponse user;
}
