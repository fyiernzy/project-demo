package com.demo.springbootdemo1.shared.wrapper;

import com.demo.springbootdemo1.shared.utils.ValidatorUtils;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.io.Serializable;
import java.util.Optional;

public interface Patchable<T> extends Serializable {

    @Nullable T value();

    Patchable.@NonNull State state();

    default Optional<T> optionally() {
        return Optional.ofNullable(value());
    }

    static <T> Patchable<T> withoutValue() {
        return new Patchable<>() {
            @Override
            public @Nullable T value() {
                return null;
            }

            @Override
            public @NonNull State state() {
                return State.UNSET;
            }
        };
    }

    static <T> Patchable<T> withValue(T value) {
        return new Patchable<>() {
            @Override
            public @Nullable T value() {
                return value;
            }

            @Override
            public @NonNull State state() {
                return ValidatorUtils.isNull(value) ? State.CLEAR : State.SET;
            }
        };
    }

    enum State {
        UNSET, SET, CLEAR
    }
}
