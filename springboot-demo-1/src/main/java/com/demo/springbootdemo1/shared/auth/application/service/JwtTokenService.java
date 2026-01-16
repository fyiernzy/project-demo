package com.demo.springbootdemo1.shared.auth.application.service;

import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;

@Validated
public interface JwtTokenService {

    @NonNull String createAccessToken(@NotNull Authentication authentication);

    @NonNull String createRefreshToken(@NotNull Authentication authentication);

    boolean isAccessTokenValid(String accessToken);

    boolean isRefreshTokenValid(String refreshToken);

    @Nullable String getUsername(String accessToken);

}
