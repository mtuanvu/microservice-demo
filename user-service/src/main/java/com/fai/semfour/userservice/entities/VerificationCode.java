package com.fai.semfour.userservice.entities;

import com.fai.semfour.userservice.utils.DateTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "verification_codes")
public class VerificationCode extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "verification_code_id")
    String id;

    @Column(name = "code", columnDefinition = "TEXT", nullable = false)
    String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    Type type;

    @Column(name = "expiry_time", nullable = false)
    Timestamp expiryTime;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    public enum Type {
        VERIFY_EMAIL, FORGOT_PASSWORD
    }
}
