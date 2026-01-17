package com.demo.springbootdemo1.shared.config.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateGrayscaleConfigModel(
    @NotNull @Valid GrayscaleConfigId grayscaleConfigId,
    Boolean open,
    Double percentage,
    Set<String> whitelist,
    Set<String> blacklist,
    Boolean defaultValue
) {

}
