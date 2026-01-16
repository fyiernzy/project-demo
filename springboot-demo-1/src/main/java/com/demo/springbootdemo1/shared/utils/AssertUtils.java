package com.demo.springbootdemo1.shared.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.NONE)
public class AssertUtils {

    @Contract("false, _ -> fail")
    public static <X extends RuntimeException> void state(boolean state, Supplier<X> exception) {
        isTrue(state, exception);
    }

    @Contract("false, _ -> fail")
    public static void state(boolean state, String message) {
        isTrue(state, () -> new IllegalStateException(message));
    }

    @Contract("null, _ -> fail")
    public static <X extends RuntimeException> void notEmpty(Collection<?> collection,
                                                             Supplier<X> exception) {
        isTrue(collection != null && !collection.isEmpty(), exception);
    }

    @Contract("null, _ -> fail")
    public static void notEmpty(Collection<?> collection,
                                String message) {
        notEmpty(collection, () -> new IllegalArgumentException(message));
    }

    @Contract("null, _ -> fail")
    public static <X extends RuntimeException> void notNull(Object obj, Supplier<X> exception) {
        isTrue(obj != null, exception);
    }

    @Contract("null, _ -> fail")
    public static void notNull(Object obj, String message) {
        isTrue(obj != null, () -> new IllegalStateException(message));
    }

    @Contract("null, _ -> fail")
    public static void notBlank(String text, Supplier<RuntimeException> exception) {
        hasText(text, exception);
    }

    @Contract("null, _ -> fail")
    public static void notBlank(String text, String label) {
        hasText(text, () -> new IllegalArgumentException(label + " is blank"));
    }

    @Contract("null, _ -> fail")
    public static void hasText(String text, Supplier<RuntimeException> exception) {
        isTrue(StringUtils.hasText(text), exception);
    }

    @Contract("true, _ -> fail")
    public static void isFalse(boolean condition, Supplier<RuntimeException> exception) {
        isTrue(!condition, exception);
    }

    @Contract("false, _ -> fail")
    public static <X extends RuntimeException> void isTrue(boolean condition,
                                                           Supplier<X> exception) {
        if (!condition) {
            throw exception.get();
        }
    }

    @Contract("false, _ -> fail")
    public static void isTrue(boolean condition, String message) {
        isTrue(condition, () -> new IllegalStateException(message));
    }
}
