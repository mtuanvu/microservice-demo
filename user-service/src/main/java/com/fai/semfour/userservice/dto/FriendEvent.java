package com.fai.semfour.userservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendEvent {
    private String userId;
    private String friendId;
    private String eventType;
}
