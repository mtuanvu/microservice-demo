package com.fai.semfour.userservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AccountResponse {
    String id;
    String username;
    Timestamp deletedAt;
    boolean systemBlock;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
