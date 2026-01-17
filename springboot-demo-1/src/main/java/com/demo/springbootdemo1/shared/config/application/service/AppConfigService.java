package com.demo.springbootdemo1.shared.config.application.service;

import com.demo.springbootdemo1.shared.config.application.dto.AppConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.CreateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.application.dto.UpdateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfigId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AppConfigService {

    @NonNull AppConfigModel createAppConfig(@NotNull @Valid CreateAppConfigCommand createAppConfigCommand);

    @NonNull AppConfigModel updateAppConfig(@NotNull @Valid UpdateAppConfigCommand updateAppConfigCommand);

    @NonNull AppConfigModel findAppConfigById(@NotNull @Valid AppConfigId appConfigId);

    @NonNull <T> T getConfigAs(@NotNull @Valid AppConfigId appConfigId, @NotNull Class<T> clazz);

    void deleteAppConfigById(@NotNull @Valid AppConfigId appConfigId);
}