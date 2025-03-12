package com.fai.semfour.userservice.entities;

import com.fai.semfour.userservice.utils.DateTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "access_keys")
public class AccessKey extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "access_key_id")
    String id;

    @Column(name = "access_token", nullable = false, unique = true)
    String accessToken;

    @Column(name = "refresh_token")
    String refreshToken;

    @Column(name = "device_id", nullable = false)
    String deviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type", nullable = false)
    DeviceType deviceType;

    @Column(name = "last_used", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime lastUsed;

    @Column(name = "is_active", nullable = false)
    Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public enum DeviceType {
        WEB, MOBILE
    }

}
