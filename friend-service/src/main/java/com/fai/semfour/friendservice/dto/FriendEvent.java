package com.fai.semfour.friendservice.dto;

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
