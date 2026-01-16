package com.demo.springbootdemo1.config.security.handler;

import com.demo.springbootdemo1.config.security.AuthErrorCodeEnum;
import com.demo.springbootdemo1.shared.error.ErrorCode;
import com.demo.springbootdemo1.shared.i18n.I18nService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final I18nService i18nService;

    @Override
    public void commence(@NotNull HttpServletRequest request,
                         HttpServletResponse response,
                         @NotNull AuthenticationException exception) throws IOException {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorCode errorCode = AuthErrorCodeEnum.UNAUTHENTICATED;

        if (response.isCommitted()) {
            log.debug("Response already committed for {} {}.",
                request.getMethod(), request.getRequestURI(), exception);
            return;
        }

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.setStatus(status.value());
            return;
        }

        String traceId = Optional.ofNullable(extractTraceId(request)).orElse("");

        Locale locale = request.getLocale();
        String message = i18nService.resolveMessage(errorCode.code(), locale);

        // Create ProblemDetail
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle(message);
        problemDetail.setDetail(message);
        problemDetail.setProperty("method", request.getMethod());
        problemDetail.setProperty("traceId", traceId);
        problemDetail.setProperty("timestamp", Instant.now().toString());

        // Write Response
        response.resetBuffer();
        response.setStatus(status.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(problemDetail));
        response.flushBuffer();

        log.info("Unauthorized access: {} {} principal={}", request.getMethod(),
            request.getRequestURI(), request.getUserPrincipal(), exception);
    }

    private String extractTraceId(HttpServletRequest request) {
        String traceId = request.getHeader("X-Request-Id");
        if (traceId == null || traceId.isBlank()) {
            traceId = request.getHeader("X-Correlation-Id");
        }
        return (traceId == null || traceId.isBlank()) ? null : traceId;
    }
}
