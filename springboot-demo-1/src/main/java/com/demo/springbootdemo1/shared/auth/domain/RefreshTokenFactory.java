package com.demo.springbootdemo1.shared.auth.domain;

import com.demo.springbootdemo1.shared.utils.AuthenticationUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class RefreshTokenFactory {

    public RefreshToken createRefreshToken(Authentication authentication) {
        CustomUserDetails userDetails = AuthenticationUtils.deriveUserDetails(authentication).orElseThrow();
        Instant expiresAt = Instant.now().plusSeconds(Duration.ofHours(1).toSeconds());
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRevoked(false);
        refreshToken.setExpiresAt(expiresAt);
        refreshToken.setUserId(String.valueOf(userDetails.getId()));
        refreshToken.setUsername(userDetails.getUsername());
        return refreshToken;
    }
}
