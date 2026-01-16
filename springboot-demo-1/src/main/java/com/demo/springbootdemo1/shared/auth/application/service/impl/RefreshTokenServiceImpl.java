package com.demo.springbootdemo1.shared.auth.application.service.impl;

import com.demo.springbootdemo1.shared.auth.application.service.RefreshTokenService;
import com.demo.springbootdemo1.shared.auth.domain.RefreshToken;
import com.demo.springbootdemo1.shared.auth.domain.RefreshTokenFactory;
import com.demo.springbootdemo1.shared.auth.domain.RefreshTokenRepository;
import com.demo.springbootdemo1.shared.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenFactory refreshTokenFactory;

    @Override
    public @NonNull RefreshToken createRefreshToken(Authentication authentication) {
        // 1. Revoke old refresh token
        String userId = AuthenticationUtils.deriveUserIdAsString(authentication);
        revokeByUserId(userId);

        // 2. Create new refresh token
        RefreshToken newRefreshToken = refreshTokenFactory.createRefreshToken(authentication);
        return refreshTokenRepository.save(newRefreshToken);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isRefreshTokenValid(String refreshTokenId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenId).orElse(null);
        return refreshToken != null && !refreshToken.isRevoked() && !refreshToken.isExpired();
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken findById(String id) {
        return refreshTokenRepository.findById(id).orElse(null);
    }

    @Override
    public void revokeByRefreshTokenId(String refreshTokenId) {
        refreshTokenRepository.revokeByRefreshTokenId(refreshTokenId);
    }

    @Override
    public void revokeByUserId(String userId) {
        refreshTokenRepository.revokeByUserId(userId);
    }

}
