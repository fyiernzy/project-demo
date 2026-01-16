package com.demo.springbootdemo1.shared.config.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AppConfigCode(
    @NotBlank String application,
    @NotBlank String module,
    @NotBlank String name
) {

}
