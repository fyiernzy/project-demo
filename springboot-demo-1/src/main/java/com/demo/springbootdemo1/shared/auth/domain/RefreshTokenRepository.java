package com.demo.springbootdemo1.shared.auth.domain;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<@NonNull RefreshToken, @NonNull String> {

    @Modifying
    @Query("UPDATE RefreshToken r SET r.revoked = true WHERE r.id = :refreshTokenId")
    void revokeByRefreshTokenId(String refreshTokenId);

    @Modifying
    @Query("UPDATE RefreshToken r SET r.revoked = true WHERE r.userId = :userId")
    void revokeByUserId(String userId);
}
