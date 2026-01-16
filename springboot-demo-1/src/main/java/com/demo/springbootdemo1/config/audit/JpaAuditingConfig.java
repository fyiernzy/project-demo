package com.demo.springbootdemo1.config.audit;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<@NonNull String> auditorAware() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null
                                  && authentication.isAuthenticated()
                                  && !Objects.equals(authentication.getPrincipal(), "anonymousUser");
        return isAuthenticated
               ? () -> Optional.ofNullable(authentication.getName())
               : () -> Optional.of("ANONYMOUS");
    }
}
