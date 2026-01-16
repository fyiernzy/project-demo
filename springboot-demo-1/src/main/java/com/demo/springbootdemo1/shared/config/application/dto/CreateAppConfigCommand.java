package com.demo.springbootdemo1.shared.config.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreateAppConfigCommand(
    @Valid AppConfigCode appConfigCode,
    @NotBlank String value,
    String description
) {

}
