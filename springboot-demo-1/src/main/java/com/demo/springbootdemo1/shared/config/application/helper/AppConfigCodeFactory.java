package com.demo.springbootdemo1.shared.config.application.helper;

import com.demo.springbootdemo1.shared.config.application.dto.AppConfigCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfigCodeFactory {

    // Effectively final
    @Value("${spring.application.name}")
    private String application;

    // Effectively final
    @Value("${modules.default}")
    private String module;

    public AppConfigCode create(String appConfigName) {
        return new AppConfigCode(application, module, appConfigName);
    }

    public AppConfigCode create(String module, String appConfigName) {
        return new AppConfigCode(application, module, appConfigName);
    }

    public AppConfigCode create(String application, String module, String appConfigName) {
        return new AppConfigCode(application, module, appConfigName);
    }

}
