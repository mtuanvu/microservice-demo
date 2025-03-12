package com.fai.semfour.userservice.entities;

import com.fai.semfour.userservice.utils.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uid")
    String id;

    @Column(name = "first_name", nullable = false, length = 20)
    String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    String lastName;

    @Column(name = "birth_date", nullable = false)
    LocalDate birthDate;

    @Email
    @Column(name = "email", nullable = false, length = 100, unique = true)
    String email;

    @Column(name = "phone", nullable = false, length = 11, unique = true)
    String phone;

    @Column(name = "avatar_url", length = 225)
    String avatarUrl;

    @Column(name = "background_url", length = 225)
    String backgroundUrl;

    @Column(name = "bio", length = 225)
    String bio;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    Gender gender;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", unique = true, nullable = false)
    private Account account;

    public enum Gender {
        MALE, FEMALE
    }
}
