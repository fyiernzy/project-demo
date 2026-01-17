package com.demo.springbootdemo1.shared.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ValidatorUtils {


    public static boolean isTrue(boolean condition) {
        return condition;
    }

    public static boolean isTrue(Boolean condition) {
        return condition;
    }

    public static boolean isFalse(boolean condition) {
        return !condition;
    }

    public static boolean isNotNull(Object object) {
        return Objects.nonNull(object);
    }

    public static boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    public static boolean isEqual(Object a, Object b) {
        return Objects.equals(a, b);
    }

    public static boolean isNotEqual(Object a, Object b) {
        return !Objects.equals(a, b);
    }

    public static boolean isNotBlank(String str) {
        return StringUtils.hasText(str);
    }

    public static boolean isBlank(String str) {
        return !StringUtils.hasText(str);
    }

    public static boolean isEqualIgnoreCase(String a, String b) {
        return StringUtils.hasText(a) && StringUtils.hasText(b) && a.equalsIgnoreCase(b);
    }

    public static boolean isEmpty(Object[] arr) {
        return Objects.isNull(arr) || arr.length == 0;
    }


    public static boolean isNotEmpty(Object[] arr) {
        return !isEmpty(arr);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !CollectionUtils.isEmpty(map);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }
}
