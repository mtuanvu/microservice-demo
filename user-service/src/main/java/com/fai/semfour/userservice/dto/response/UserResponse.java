package com.fai.semfour.userservice.dto.response;

import com.fai.semfour.userservice.entities.User.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    String phone;
    String avatarUrl;
    String backgroundUrl;
    String bio;
    Gender gender;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
