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
@Builder
public class AccessKey extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "access_key_id")
    String id;

    @Column(name = "access_token", nullable = false, unique = true, length = 300)
    String accessToken;

    @Column(name = "refresh_token", length = 300)
    String refreshToken;

    @Column(name = "device_id", nullable = false)
    String deviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type", nullable = false)
    DeviceType deviceType;

    @Column(name = "last_used")
    LocalDateTime lastUsed = LocalDateTime.now();

    @Column(name = "is_active")
    Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    public enum DeviceType {
        WEB, MOBILE
    }

}
