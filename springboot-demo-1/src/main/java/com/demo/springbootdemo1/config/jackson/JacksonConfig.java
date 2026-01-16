package com.demo.springbootdemo1.config.jackson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {

    public static final String DEFAULT_MAPPER = "DEFAULT";

    @Primary
    @Bean(DEFAULT_MAPPER)
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }
}
