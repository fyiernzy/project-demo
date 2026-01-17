package com.demo.springbootdemo1.shared.config.application.dto;

import com.demo.springbootdemo1.shared.config.domain.entity.AppConfigId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAppConfigCommand(
    @NotNull @Valid AppConfigId appConfigId,
    @NotBlank String value,
    String description
) {

    public static CreateAppConfigCommand of(AppConfigId appConfigId, String value) {
        return new CreateAppConfigCommand(appConfigId, value, value);
    }

}
