package com.demo.springbootdemo1.shared.config.application.service;

import com.demo.springbootdemo1.shared.config.application.dto.CreateGrayscaleConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.GrayscaleConfigId;
import com.demo.springbootdemo1.shared.config.application.dto.GrayscaleConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.UpdateGrayscaleConfigModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface GrayscaleService {

    @NonNull GrayscaleConfigModel createGrayscaleConfig(@NotNull @Valid CreateGrayscaleConfigModel createGrayscaleConfigModel);

    @NonNull GrayscaleConfigModel updateGrayscaleConfig(@NotNull @Valid UpdateGrayscaleConfigModel updateGrayscaleConfigModel);

    boolean isFeatureEnabled(@NotNull @Valid GrayscaleConfigId grayscaleConfigId, String userId);

    void deleteGrayscaleConfigById(@NotNull @Valid GrayscaleConfigId grayscaleConfigId);
}
