
package com.demo.springbootdemo1.shared.config.application.helper;

import com.demo.springbootdemo1.shared.config.application.dto.AppConfigCode;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfigId;
import com.demo.springbootdemo1.shared.environment.EnvironmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppConfigIdFactory {

    private final EnvironmentService environmentService;

    public AppConfigId create(AppConfigCode appConfigCode) {
        return create(appConfigCode.application(), appConfigCode.module(), appConfigCode.name());
    }

    public AppConfigId create(String application, String name, String module) {
        return new AppConfigId(application, module, name, environmentService.getDeploymentStage());
    }
}
