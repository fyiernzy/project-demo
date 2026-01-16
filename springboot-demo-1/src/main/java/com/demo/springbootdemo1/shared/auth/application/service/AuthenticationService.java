package com.demo.springbootdemo1.shared.auth.application.service;


import com.demo.springbootdemo1.shared.auth.application.dto.LoginRequest;
import com.demo.springbootdemo1.shared.auth.application.dto.LoginResponse;
import com.demo.springbootdemo1.shared.auth.application.dto.RefreshTokenRequest;
import com.demo.springbootdemo1.shared.auth.application.dto.RefreshTokenResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthenticationService {

    @NonNull LoginResponse login(@NotNull @Valid LoginRequest loginRequest);

    @NonNull RefreshTokenResponse refresh(@NotNull @Valid RefreshTokenRequest refreshTokenRequest);
}
