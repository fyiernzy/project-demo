package com.demo.springbootdemo1.shared.config.application.dto;

import com.demo.springbootdemo1.shared.environment.DeploymentStageEnum;

public record AppConfigModel(
    String application,
    String name,
    DeploymentStageEnum environment,
    String value,
    String description,
    boolean active,
    Long version
) {

}
