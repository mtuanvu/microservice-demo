package com.fai.semfour.friendservice.dto.response;

import com.fai.semfour.friendservice.entities.FriendRequest.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendRequestResponse {
    String id;
    String senderId;
    String receiverId;
    Status status;
    LocalDateTime createdAt;
}
