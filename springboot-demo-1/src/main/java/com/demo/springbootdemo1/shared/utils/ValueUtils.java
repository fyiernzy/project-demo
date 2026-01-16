package com.demo.springbootdemo1.shared.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
}
