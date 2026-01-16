package com.demo.springbootdemo1.shared.auth.web;

import com.demo.springbootdemo1.shared.auth.application.dto.LoginRequest;
import com.demo.springbootdemo1.shared.auth.application.dto.LoginResponse;
import com.demo.springbootdemo1.shared.auth.application.dto.RefreshTokenRequest;
import com.demo.springbootdemo1.shared.auth.application.dto.RefreshTokenResponse;
import com.demo.springbootdemo1.shared.auth.application.service.AuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@NotNull @Valid @RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refresh(@NotNull @Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authenticationService.refresh(refreshTokenRequest);
    }
}
