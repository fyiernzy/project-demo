package com.demo.springbootdemo1.shared.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class ValueUtils {

    public static String orEmpty(String value) {
        return ValidatorUtils.isBlank(value) ? "" : value;
    }

    public static String ifPresentOrElse(String value, Supplier<String> orElse) {
        return ifPresentOrElse(value, Function.identity(), orElse);
    }

    public static String ifPresentOrElse(String value,
                                         Function<String, String> ifPresent,
                                         Supplier<String> orElse) {
        return ifPresentOrElse(value, ValidatorUtils::isNotBlank, ifPresent, orElse);
    }

    public static <T, U> U ifPresentOrElse(T value,
                                           Predicate<T> isPresent,
                                           Function<T, U> ifPresent,
                                           Supplier<U> orElse) {
        return isPresent.test(value) ? ifPresent.apply(value) : orElse.get();
    }

    public static <T> Optional<T> cast(Object object, Class<T> clazz) {
        if (ValidatorUtils.isNull(object)) {
            return Optional.empty();
        }
        if (clazz.isInstance(object) || clazz.isAssignableFrom(object.getClass())) {
            return Optional.of(clazz.cast(object));
        }
        try {
            return Optional.ofNullable(StaticUtils.objectMapper().convertValue(object, clazz));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> cast(String value, Class<T> clazz) {
        if (ValidatorUtils.isBlank(value)) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(StaticUtils.objectMapper().convertValue(value, clazz));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public static <T, X extends RuntimeException> T castOrThrow(Object value,
                                                                Class<T> clazz,
                                                                Supplier<X> exceptionSupplier) {
        return cast(value, clazz).orElseThrow(exceptionSupplier);
    }

    public static <T, X extends RuntimeException> T castOrThrow(String value,
                                                                Class<T> clazz,
                                                                Supplier<X> exceptionSupplier) {
        return cast(value, clazz).orElseThrow(exceptionSupplier);
    }
}
