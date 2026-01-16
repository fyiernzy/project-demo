package com.demo.springbootdemo1.config.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Springboot Demo API",
        version = "v1",
        description = "API documentation for springboot-demo-1",
        license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
    )
)
public class OpenApiConfig {
}
