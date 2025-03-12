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
@Table(name = "friend_requests", uniqueConstraints = @UniqueConstraint(columnNames = {"sender_id", "receiver_id"}))
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    String id;

    @Column(name = "sender_id", nullable = false)
    String senderId;

    @Column(name = "receiver_id", nullable = false)
    String receiverId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status = Status.PENDING;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }
}
