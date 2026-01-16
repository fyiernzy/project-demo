package com.demo.springbootdemo1.shared.error;

import org.jspecify.annotations.NonNull;

public interface ErrorCode {

    @NonNull String code();

    @NonNull String message();
}
