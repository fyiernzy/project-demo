package com.demo.springbootdemo1.shared.auth.application.service.impl;

import com.demo.springbootdemo1.shared.auth.application.dto.LoginRequest;
import com.demo.springbootdemo1.shared.auth.application.dto.LoginResponse;
import com.demo.springbootdemo1.shared.auth.application.dto.RefreshTokenRequest;
import com.demo.springbootdemo1.shared.auth.application.dto.RefreshTokenResponse;
import com.demo.springbootdemo1.shared.auth.application.service.AuthenticationService;
import com.demo.springbootdemo1.shared.auth.application.service.CustomUserDetailsService;
import com.demo.springbootdemo1.shared.auth.application.service.JwtTokenService;
import com.demo.springbootdemo1.shared.auth.application.service.RefreshTokenService;
import com.demo.springbootdemo1.shared.auth.domain.RefreshToken;
import com.demo.springbootdemo1.shared.user.application.dto.UserModel;
import com.demo.springbootdemo1.shared.user.application.service.UserService;
import com.demo.springbootdemo1.shared.utils.AssertUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenService jwtTokenService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public @NonNull LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()
            )
        );

        String username = loginRequest.username();
        UserModel userModel = userService.findUserByUsername(username);
        AssertUtils.notNull(userModel, "User not found");

        String accessToken = jwtTokenService.createAccessToken(authentication);
        String refreshToken = jwtTokenService.createRefreshToken(authentication);

        return new LoginResponse(accessToken, refreshToken, userModel);
    }

    @Override
    public @NonNull RefreshTokenResponse refresh(@NotNull @Valid RefreshTokenRequest refreshTokenRequest) {
        // 1. Check if the refreshToken is valid
        String refreshTokenId = refreshTokenRequest.refreshToken();
        AssertUtils.state(jwtTokenService.isRefreshTokenValid(refreshTokenId),
            "Invalid refresh token");

        // 2. Build the Authentication to create new accessToken
        RefreshToken refreshToken = refreshTokenService.findById(refreshTokenId);
        AssertUtils.notNull(refreshToken, "Invalid refresh token");
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(
            refreshToken.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
            userDetails.getPassword(), userDetails.getAuthorities());
        String accessToken = jwtTokenService.createAccessToken(authentication);

        // 3. Return the RefreshToken
        return new RefreshTokenResponse(accessToken);
    }
}
