package com.demo.springbootdemo1.shared.config.domain.entity;

import com.demo.springbootdemo1.shared.environment.DeploymentStageEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public record AppConfigId(
    @Column(name = "application", nullable = false)
    String application,

    @Column(name = "module", nullable = false)
    String module,

    @Column(name = "name", nullable = false)
    String name,

    @Enumerated(EnumType.STRING)
    @Column(name = "deployment_stage", nullable = false)
    DeploymentStageEnum deploymentStage
) {
}
