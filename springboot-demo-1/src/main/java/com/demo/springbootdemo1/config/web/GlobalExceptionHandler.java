package com.demo.springbootdemo1.config.web;

import com.demo.springbootdemo1.shared.error.GenericException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ErrorResponse handleAuthorizationDenied(AuthorizationDeniedException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder(
            exception,
            HttpStatus.FORBIDDEN,
            exception.getMessage()
        ).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidation(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        List<String> errors = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
            .toList();

        return ErrorResponse.builder(
            exception,
            HttpStatus.BAD_REQUEST,
            String.join(",", errors)
        ).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);
        List<String> errors = exception.getConstraintViolations()
            .stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .toList();

        return ErrorResponse.builder(
            exception,
            HttpStatus.BAD_REQUEST,
            String.join(",", errors)
        ).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder(
            exception,
            HttpStatus.BAD_REQUEST,
            exception.getMessage()
        ).build();
    }

    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponse handleIllegalStateException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder(
            exception,
            HttpStatus.BAD_REQUEST,
            exception.getMessage()
        ).build();
    }

    @ExceptionHandler(GenericException.class)
    public ErrorResponse handleGenericException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder(
            exception,
            HttpStatus.BAD_REQUEST,
            exception.getMessage()
        ).build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAll(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder(
            exception,
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error"
        ).build();
    }
}
