package com.fai.semfour.userservice.entities;

import com.fai.semfour.userservice.utils.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "accounts")
public class Account extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    String id;

    @Email
    @Column(name = "email", nullable = false, length = 100, unique = true)
    String email;

    @Column(name = "password", nullable = false, length = 255)
    String password;

    @Column(name = "deleted_at")
    Timestamp deletedAt;

    @Column(name = "system_block")
    boolean systemBlock;

    @ManyToMany
    @JoinTable(name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"account_id", "role_id"}))
    private Set<Role> roles;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccessKey> accessKeys;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<VerificationCode> verificationCodes;
}
