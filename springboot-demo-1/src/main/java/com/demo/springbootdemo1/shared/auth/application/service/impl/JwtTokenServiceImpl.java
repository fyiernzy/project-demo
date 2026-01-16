package com.demo.springbootdemo1.shared.auth.application.service.impl;

import com.demo.springbootdemo1.shared.auth.application.service.JwtTokenService;
import com.demo.springbootdemo1.shared.auth.application.service.RefreshTokenService;
import com.demo.springbootdemo1.shared.auth.domain.CustomUserDetails;
import com.demo.springbootdemo1.shared.auth.domain.RefreshToken;
import com.demo.springbootdemo1.shared.utils.AssertUtils;
import com.demo.springbootdemo1.shared.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private static final String JWT_UID = "uid";
    private static final String[] JWT_CLAIM_SET = {JWT_UID};

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final RefreshTokenService refreshTokenService;
    private final long ACCESS_TOKEN_TTL_MIN = 15;

    @Override
    public @NonNull String createAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant exp = now.plus(Duration.ofMinutes(ACCESS_TOKEN_TTL_MIN));
        CustomUserDetails userDetails = AuthenticationUtils.deriveUserDetails(authentication)
            .orElseThrow();

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuedAt(now)
            .notBefore(now)
            .expiresAt(exp)
            .subject(userDetails.getUsername())
            .claim(JWT_UID, userDetails.getId())
            .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public @NonNull String createRefreshToken(Authentication authentication) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication);
        return refreshToken.getId();
    }

    @Override
    public boolean isAccessTokenValid(String accessToken) {
        try {
            Jwt jwt = jwtDecoder.decode(accessToken);
            for (String claim : JWT_CLAIM_SET) {
                AssertUtils.notNull(jwt.getClaim(claim), "Missing " + claim + " on JWT");
            }
            Instant expiresAt = jwt.getExpiresAt();
            return expiresAt != null && Instant.now().isBefore(expiresAt);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean isRefreshTokenValid(String refreshToken) {
        return refreshTokenService.isRefreshTokenValid(refreshToken);
    }

    @Override
    public @Nullable String getUsername(String accessToken) {
        try {
            Jwt jwt = jwtDecoder.decode(accessToken);
            return jwt.getSubject();
        } catch (JwtException ex) {
            return null;
        }
    }
}
