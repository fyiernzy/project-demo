package com.demo.springbootdemo1.config.security;

import com.demo.springbootdemo1.config.security.handler.CustomAccessDeniedHandler;
import com.demo.springbootdemo1.config.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        AuthenticationManager authenticationManager,
        CustomAccessDeniedHandler customAccessDeniedHandler,
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint
    ) throws Exception {
        return http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authenticationManager(authenticationManager)
            .authorizeHttpRequests(registry -> registry
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .requestMatchers("/api/v1/weather/**").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(e -> e
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint)
            )
            .formLogin(AbstractHttpConfigurer::disable)
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults())
            )
            .build();
    }

}
