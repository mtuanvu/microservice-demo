package com.fai.semfour.userservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AccountResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    String phone;
    String avatarUrl;
    String backgroundUrl;
    String bio;
    String gender;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<RoleResponse> roles;
}
