package com.fai.semfour.friendservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlockedUserResponse {
    String id;
    String userId;
    String blockedUserId;
    LocalDateTime createdAt;
}
