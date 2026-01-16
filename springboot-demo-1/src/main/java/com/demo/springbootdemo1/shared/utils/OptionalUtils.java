package com.demo.springbootdemo1.shared.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class OptionalUtils {

    public static Optional<String> notBlank(String value) {
        return Optional.ofNullable(value).filter(s -> !s.isBlank());
    }
}
