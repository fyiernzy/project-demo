package com.demo.springbootdemo1.shared.config.application.service;

import com.demo.springbootdemo1.shared.config.application.dto.AppConfigCode;
import com.demo.springbootdemo1.shared.config.application.dto.AppConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.CreateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.application.dto.UpdateAppConfigCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AppConfigService {

    @NonNull
    AppConfigModel createAppConfig(@NotNull @Valid CreateAppConfigCommand createAppConfigCommand);

    @NonNull
    AppConfigModel updateAppConfig(@NotNull @Valid UpdateAppConfigCommand updateAppConfigCommand);

    @NonNull
    AppConfigModel findAppConfigById(@NotNull @Valid AppConfigCode appConfigCode);

    void deleteAppConfigById(@NotNull @Valid AppConfigCode appConfigCode);
}