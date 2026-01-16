package com.demo.springbootdemo1.shared.environment;


import org.jspecify.annotations.NonNull;

public interface EnvironmentService {

    @NonNull
    DeploymentStageEnum getDeploymentStage();
}
