package com.demo.springbootdemo1.config.security;

import com.demo.springbootdemo1.shared.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

@RequiredArgsConstructor
public enum AuthErrorCodeEnum implements ErrorCode {
    UNAUTHENTICATED("AUTH001", "auth.unauthenticated"),
    UNAUTHORIZED("AUTH002", "auth.unauthorized"),
    ;

    private final String code;
    private final String message;

    @Override
    public @NonNull String code() {
        return code;
    }

    @Override
    public @NotNull String message() {
        return message;
    }
}
