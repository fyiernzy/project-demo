package com.demo.springbootdemo1.config.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableConfigurationProperties(CorsConfig.CorsProperties.class)
class CorsConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource(CorsProperties corsProperties) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(corsProperties.allowedOrigins());
        corsConfiguration.setAllowedMethods(corsProperties.allowedMethods());
        corsConfiguration.setAllowedHeaders(corsProperties.allowedHeaders());
        corsConfiguration.setExposedHeaders(corsProperties.exposedHeaders());
        corsConfiguration.setAllowCredentials(corsProperties.allowCredentials());

        if (corsProperties.maxAge() != null) {
            corsConfiguration.setMaxAge(corsProperties.maxAge());
        }

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @ConfigurationProperties(prefix = "security.cors")
    record CorsProperties(
        List<String> allowedOrigins,
        List<String> allowedMethods,
        List<String> allowedHeaders,
        List<String> exposedHeaders,
        boolean allowCredentials,
        Duration maxAge
    ) {

    }
}
