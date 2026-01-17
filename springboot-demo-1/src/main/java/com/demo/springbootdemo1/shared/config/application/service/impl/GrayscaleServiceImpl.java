package com.demo.springbootdemo1.shared.config.application.service.impl;

import com.demo.springbootdemo1.shared.config.application.dto.*;
import com.demo.springbootdemo1.shared.config.application.mapper.GrayscaleConfigMapper;
import com.demo.springbootdemo1.shared.config.application.service.AppConfigService;
import com.demo.springbootdemo1.shared.config.application.service.GrayscaleService;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfigId;
import com.demo.springbootdemo1.shared.utils.ValidatorUtils;
import com.demo.springbootdemo1.shared.utils.ValueUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrayscaleServiceImpl implements GrayscaleService {

    private final AppConfigService appConfigService;
    private final GrayscaleConfigMapper grayscaleConfigMapper;

    @Override
    public @NonNull GrayscaleConfigModel createGrayscaleConfig(CreateGrayscaleConfigModel createGrayscaleConfigModel) {
        GrayscaleConfigModel grayscaleConfigModel = grayscaleConfigMapper.toModel(
            createGrayscaleConfigModel);
        AppConfigId appConfigId = toAppConfigId(grayscaleConfigModel.grayscaleConfigId());
        String value = serialize(grayscaleConfigModel);
        return deserialize(
            appConfigService.createAppConfig(CreateAppConfigCommand.of(appConfigId, value))
                .value());
    }

    @Override
    public @NonNull GrayscaleConfigModel updateGrayscaleConfig(UpdateGrayscaleConfigModel updateGrayscaleConfigModel) {
        GrayscaleConfigModel grayscaleConfigModel = grayscaleConfigMapper.toModel(
            updateGrayscaleConfigModel);
        AppConfigId appConfigId = toAppConfigId(grayscaleConfigModel.grayscaleConfigId());
        String value = serialize(grayscaleConfigModel);
        return deserialize(
            appConfigService.updateAppConfig(UpdateAppConfigCommand.of(appConfigId, value))
                .value());
    }

    @Override
    public boolean isFeatureEnabled(GrayscaleConfigId grayscaleConfigId, String userId) {
        AppConfigId appConfigId = toAppConfigId(grayscaleConfigId);
        GrayscaleConfigModel grayscaleConfigModel = deserialize(
            appConfigService.findAppConfigById(appConfigId).value());

        Set<String> blacklist = grayscaleConfigModel.blacklist();
        if (ValidatorUtils.isNotNull(blacklist) && blacklist.contains(userId)) {
            return false;
        }

        Boolean open = grayscaleConfigModel.open();
        if (ValidatorUtils.isTrue(open)) {
            return true;
        }

        Double percentage = grayscaleConfigModel.percentage();
        if (ValidatorUtils.isNotNull(percentage)
            && shunt(percentage, userId, grayscaleConfigId.feature())) {
            return true;
        }

        Set<String> whitelist = grayscaleConfigModel.whitelist();
        if (ValidatorUtils.isNotNull(whitelist) && whitelist.contains(userId)) {
            return true;
        }

        Boolean defaultValue = grayscaleConfigModel.defaultValue();
        if (ValidatorUtils.isNotNull(defaultValue)) {
            return defaultValue;
        }
        return false;
    }

    @Override
    public void deleteGrayscaleConfigById(GrayscaleConfigId grayscaleConfigId) {
        AppConfigId appConfigId = toAppConfigId(grayscaleConfigId);
        appConfigService.deleteAppConfigById(appConfigId);
    }

    private AppConfigId toAppConfigId(GrayscaleConfigId grayscaleConfigId) {
        return new AppConfigId(
            grayscaleConfigId.application(),
            grayscaleConfigId.module(),
            grayscaleConfigId.feature()
        );
    }

    private String serialize(GrayscaleConfigModel grayscaleConfigModel) {
        return ValueUtils.castOrThrow(grayscaleConfigModel, String.class,
            () -> new IllegalStateException("Failed to serialize grayscale config")
        );
    }

    private GrayscaleConfigModel deserialize(String value) {
        return ValueUtils.castOrThrow(value, GrayscaleConfigModel.class,
            () -> new IllegalStateException("Failed to deserialize grayscale config")
        );
    }

    private boolean shunt(double percentage, String userId, String feature) {
        if (ValidatorUtils.isBlank(userId)
            || ValidatorUtils.isBlank(feature)
            || percentage < 0
            || percentage > 100) {
            return false;
        }
        String hashString = userId + feature;
        int hash = Math.abs(hashString.hashCode());
        int bucket = hash % 100;
        return bucket < percentage;
    }
}
