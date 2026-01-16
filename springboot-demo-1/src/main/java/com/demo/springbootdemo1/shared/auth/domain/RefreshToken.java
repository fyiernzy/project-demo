package com.demo.springbootdemo1.shared.auth.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Table(name = "refresh_tokens")
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private String userId;

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "expires_at", nullable = false, updatable = false)
    private Instant expiresAt;

    @Column(name = "revoked", nullable = false, updatable = false)
    private boolean revoked;

    public boolean isExpired() {
        return expiresAt.isBefore(Instant.now());
    }
}
