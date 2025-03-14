package com.fai.semfour.userservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleResponse {
    Long id;
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    private Set<PermissionResponse> permissions;
}
