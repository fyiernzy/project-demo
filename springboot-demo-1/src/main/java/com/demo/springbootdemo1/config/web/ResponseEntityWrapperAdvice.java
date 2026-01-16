package com.demo.springbootdemo1.config.web;

import com.demo.springbootdemo1.shared.utils.ValidatorUtils;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

@RestControllerAdvice
public class ResponseEntityWrapperAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> type = returnType.getParameterType();
        return !ResponseEntity.class.isAssignableFrom(type);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (ValidatorUtils.isNull(body)) {
            return null;
        }

        HttpStatus statusFromAnnotation = resolveResponseStatus(returnType);
        if (statusFromAnnotation != null) {
            return ResponseEntity.status(statusFromAnnotation).body(body);
        }

        if (response instanceof ServletServerHttpResponse servletResponse) {
            int rawStatus = servletResponse.getServletResponse().getStatus();
            HttpStatus status = HttpStatus.resolve(rawStatus);
            return ResponseEntity
                .status(status != null ? status : HttpStatus.OK)
                .body(body);
        }

        return ResponseEntity.ok(body);
    }

    @Nullable
    private static HttpStatus resolveResponseStatus(MethodParameter returnType) {
        // 1. Check and resolve ResponseStatus from the method, if present
        Method method = Objects.requireNonNull(returnType.getMethod());
        ResponseStatus responseStatus =
            Optional.ofNullable(
                    AnnotatedElementUtils.findMergedAnnotation(method, ResponseStatus.class)
                )
                .or(() -> Optional.ofNullable(
                    AnnotatedElementUtils.findMergedAnnotation(
                        returnType.getContainingClass(), ResponseStatus.class
                    )
                ))
                .orElse(null);

        if (ValidatorUtils.isNull(responseStatus)) {
            return null;
        }

        // 2. Return the HttpStatus from the method
        HttpStatus httpStatus = responseStatus.code();
        if (httpStatus != HttpStatus.INTERNAL_SERVER_ERROR) {
            return httpStatus;
        }

        HttpStatus value = responseStatus.value();
        if (value != HttpStatus.INTERNAL_SERVER_ERROR) {
            return value;
        }

        return null;
    }
}
