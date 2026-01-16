package com.demo.springbootdemo1.shared.utils;

import com.demo.springbootdemo1.shared.auth.domain.CustomUserDetails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.NONE)
public class AuthenticationUtils {

    public static @Nullable String deriveUserIdAsString(Authentication authentication) {
        return deriveUserId(authentication).orElse(null);
    }

    public static @NonNull Optional<String> deriveUserId(Authentication authentication) {
        return deriveUserDetails(authentication).map(CustomUserDetails::getId);
    }

    public static @NonNull Optional<CustomUserDetails> deriveUserDetails(Authentication authentication) {
        return Optional.ofNullable(authentication)
            .map(Authentication::getPrincipal)
            .map(principal ->
                CustomUserDetails.class.isAssignableFrom(principal.getClass())
                ? (CustomUserDetails) principal : null
            );
    }

    public static @Nullable String getUsernameAsString() {
        return getUsername().orElse(null);
    }

    public static @NonNull Optional<String> getUsername() {
        return getAuthentication()
            .map(Authentication::getPrincipal)
            .map(principal ->
                CustomUserDetails.class.isAssignableFrom(principal.getClass())
                ? (CustomUserDetails) principal : null
            )
            .map(CustomUserDetails::getUsername);
    }

    public static @Nullable String getUserIdAsString() {
        return getUserId().orElse(null);
    }

    public static @NonNull Optional<String> getUserId() {
        return getAuthentication()
            .map(Authentication::getPrincipal)
            .map(principal ->
                CustomUserDetails.class.isAssignableFrom(principal.getClass())
                ? (CustomUserDetails) principal : null
            )
            .map(CustomUserDetails::getId);
    }

    public static @NonNull Optional<CustomUserDetails> getUserDetails() {
        return getAuthentication()
            .map(Authentication::getPrincipal)
            .map(principal ->
                CustomUserDetails.class.isAssignableFrom(principal.getClass())
                ? (CustomUserDetails) principal : null
            );
    }

    public static @NonNull Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }
}