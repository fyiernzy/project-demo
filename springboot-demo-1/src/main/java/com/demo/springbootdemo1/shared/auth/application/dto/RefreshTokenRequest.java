package com.demo.springbootdemo1.shared.auth.application.dto;

import jakarta.validation.constraints.NotEmpty;

public record RefreshTokenRequest(
    @NotEmpty String refreshToken
) {

}
