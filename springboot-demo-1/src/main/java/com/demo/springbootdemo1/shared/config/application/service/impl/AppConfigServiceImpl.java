package com.demo.springbootdemo1.shared.config.application.service.impl;

import com.demo.springbootdemo1.shared.config.application.dto.AppConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.CreateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.application.dto.UpdateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.application.mapper.AppConfigMapper;
import com.demo.springbootdemo1.shared.config.application.service.AppConfigService;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfig;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfigId;
import com.demo.springbootdemo1.shared.config.domain.repository.AppConfigRepository;
import com.demo.springbootdemo1.shared.utils.AssertUtils;
import com.demo.springbootdemo1.shared.utils.ValueUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppConfigServiceImpl implements AppConfigService {

    private final AppConfigRepository appConfigRepository;
    private final AppConfigMapper appConfigMapper;

    @Override
    @Transactional
    public @NonNull AppConfigModel createAppConfig(CreateAppConfigCommand createAppConfigCommand) {
        AppConfigId id = createAppConfigCommand.appConfigId();
        AssertUtils.isFalse(appConfigRepository.existsById(id),
            () -> new IllegalArgumentException("App config already exists"));
        AppConfig appConfig = appConfigMapper.toEntity(createAppConfigCommand);
        appConfig.setId(id);
        return appConfigMapper.toModel(appConfigRepository.save(appConfig));
    }

    @Override
    @Transactional
    public @NonNull AppConfigModel updateAppConfig(UpdateAppConfigCommand updateAppConfigCommand) {
        AppConfigId id = updateAppConfigCommand.appConfigId();
        AppConfig appConfig = appConfigRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("App config not found"));
        appConfigMapper.updateEntity(updateAppConfigCommand, appConfig);
        return appConfigMapper.toModel(appConfigRepository.save(appConfig));
    }


    @Override
    @Transactional(readOnly = true)
    public @NonNull AppConfigModel findAppConfigById(AppConfigId appConfigId) {
        return appConfigRepository.findById(appConfigId)
            .map(appConfigMapper::toModel)
            .orElseThrow(() -> new IllegalArgumentException("App config not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public <T> @NonNull T getConfigAs(AppConfigId appConfigId, Class<T> clazz) {
        AppConfigModel appConfigModel = findAppConfigById(appConfigId);
        return ValueUtils.cast(appConfigModel.value(), clazz).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteAppConfigById(AppConfigId appConfigId) {
        appConfigRepository.deleteById(appConfigId);
    }
}
