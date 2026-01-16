package com.demo.springbootdemo1.shared.auth.application.service;

import com.demo.springbootdemo1.shared.auth.domain.RefreshToken;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Validated
@Transactional
public interface RefreshTokenService {

    @NonNull RefreshToken createRefreshToken(@NotNull Authentication authentication);


    boolean isRefreshTokenValid(String refreshTokenId);

    @Nullable RefreshToken findById(String id);

    @Transactional(readOnly = true)
    void revokeByRefreshTokenId(String refreshTokenId);

    void revokeByUserId(String userId);
}
