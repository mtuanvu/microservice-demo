package com.fai.semfour.friendservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendRequestRequest {
    @NotBlank(message = "senderId cannot be blank")
    String senderId;

    @NotBlank(message = "receiverId cannot be blank")
    String receiverId;
}
