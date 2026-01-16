package com.demo.springbootdemo1.shared.environment;

import com.demo.springbootdemo1.shared.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {

    private final Environment environment;

    @Override
    public @NonNull DeploymentStageEnum getDeploymentStage() {
        // 1. Get active profiles from the deploymentStage
        String[] profiles = environment.getActiveProfiles();
        if (ValidatorUtils.isEmpty(profiles)) {
            return DeploymentStageEnum.DEV;
        }

        // 2. Extract deployment-stage-related information
        Set<DeploymentStageEnum> activeDeploymentStages = Arrays.stream(profiles)
            .map(DeploymentStageEnum::fromCode)
            .filter(ValidatorUtils::isNotNull)
            .collect(Collectors.toSet());

        // 3. Select the deployment stage with the highest priority
        return activeDeploymentStages.stream()
            .reduce((a, b) -> a.getPriority() > b.getPriority() ? a : b)
            .orElse(DeploymentStageEnum.DEV);
    }
}

