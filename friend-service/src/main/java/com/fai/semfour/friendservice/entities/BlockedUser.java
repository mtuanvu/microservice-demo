package com.fai.semfour.friendservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "blocked_users", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "blocked_user_id"}))
public class BlockedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    String id;

    @Column(name = "user_id", nullable = false)
    String userId;

    @Column(name = "blocked_user_id", nullable = false)
    String blockedUserId;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();
}
