package com.demo.springbootdemo1.shared.config.application.service.impl;

import com.demo.springbootdemo1.shared.config.application.dto.AppConfigCode;
import com.demo.springbootdemo1.shared.config.application.dto.AppConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.CreateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.application.dto.UpdateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.application.helper.AppConfigIdFactory;
import com.demo.springbootdemo1.shared.config.application.mapper.AppConfigMapper;
import com.demo.springbootdemo1.shared.config.application.service.AppConfigService;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfig;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfigId;
import com.demo.springbootdemo1.shared.config.domain.repository.AppConfigRepository;
import com.demo.springbootdemo1.shared.utils.AssertUtils;
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
    private final AppConfigIdFactory appConfigIdFactory;

    @Override
    @Transactional
    public @NonNull AppConfigModel createAppConfig(CreateAppConfigCommand createAppConfigCommand) {
        AppConfigId appConfigId = appConfigIdFactory.create(
            createAppConfigCommand.appConfigCode());
        AssertUtils.isFalse(appConfigRepository.existsById(appConfigId),
            () -> new IllegalArgumentException("App config already exists"));
        AppConfig appConfig = appConfigMapper.toEntity(createAppConfigCommand);
        appConfig.setAppConfigId(appConfigId);
        return appConfigMapper.toModel(appConfigRepository.save(appConfig));
    }

    @Override
    @Transactional
    public @NonNull AppConfigModel updateAppConfig(UpdateAppConfigCommand updateAppConfigCommand) {
        AppConfigId appConfigId = appConfigIdFactory.create(
            updateAppConfigCommand.appConfigCode());
        AppConfig appConfig = appConfigRepository.findById(appConfigId)
            .orElseThrow(() -> new IllegalArgumentException("App config not found"));
        appConfigMapper.updateEntity(updateAppConfigCommand, appConfig);
        return appConfigMapper.toModel(appConfigRepository.save(appConfig));
    }


    @Override
    @Transactional(readOnly = true)
    public @NonNull AppConfigModel findAppConfigById(AppConfigCode appConfigCode) {
        return appConfigRepository.findById(appConfigIdFactory.create(appConfigCode))
            .map(appConfigMapper::toModel)
            .orElseThrow(() -> new IllegalArgumentException("App config not found"));
    }

    @Override
    @Transactional
    public void deleteAppConfigById(AppConfigCode appConfigCode) {
        appConfigRepository.deleteById(appConfigIdFactory.create(appConfigCode));
    }
}
