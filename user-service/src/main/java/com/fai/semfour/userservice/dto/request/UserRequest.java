package com.fai.semfour.userservice.dto.request;

import com.fai.semfour.userservice.entities.User.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    String phone;
    String avatarUrl;
    String backgroundUrl;
    String bio;
    Gender gender;
}
