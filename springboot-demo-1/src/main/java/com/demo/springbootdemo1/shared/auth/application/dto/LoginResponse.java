package com.demo.springbootdemo1.shared.auth.application.dto;


import com.demo.springbootdemo1.shared.user.application.dto.UserModel;

public record LoginResponse(
    String accessToken,
    String refreshToken,
    String tokenType,
    UserModel user
) {

    public LoginResponse(String accessToken, String refreshToken, UserModel user) {
        this(accessToken, refreshToken, "Bearer", user);
    }
}
