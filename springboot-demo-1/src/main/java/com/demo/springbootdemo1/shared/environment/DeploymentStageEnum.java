package com.demo.springbootdemo1.shared.environment;

import com.demo.springbootdemo1.shared.utils.ValidatorUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum DeploymentStageEnum {
    DEV("dev", 1),
    UAT("uat", 2),
    PROD("prod", 3);

    private final String code;
    private final int priority;

    @Nullable
    public static DeploymentStageEnum fromCode(String code) {
        return Arrays.stream(DeploymentStageEnum.values())
            .filter(c -> ValidatorUtils.isEqualIgnoreCase(c.getCode(), code))
            .findFirst()
            .orElse(null);
    }
}
