package com.fai.semfour.userservice.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogoutRequest {
    private String accessToken;
}
