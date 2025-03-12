package com.fai.semfour.friendservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlockedUserRequest {
    @NotBlank(message = "userId cannot be blank")
    String userId;

    @NotBlank(message = "blockedUserId cannot be blank")
    String blockedUserId;
}
