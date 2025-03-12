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
@Table(name = "friends", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "friend_id"}))
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    String id;

    @Column(name = "user_id", nullable = false)
    String userId;

    @Column(name = "friend_id", nullable = false)
    String friendId;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    public Friend(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
        this.createdAt = LocalDateTime.now();
    }

}
