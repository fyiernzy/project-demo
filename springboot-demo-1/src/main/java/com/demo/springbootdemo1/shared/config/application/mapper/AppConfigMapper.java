package com.demo.springbootdemo1.shared.config.application.mapper;

import com.demo.springbootdemo1.config.mapstruct.GlobalMapperConfig;
import com.demo.springbootdemo1.shared.config.application.dto.AppConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.CreateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.application.dto.UpdateAppConfigCommand;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = GlobalMapperConfig.class)
public interface AppConfigMapper {

    @Mapping(target = "application", source = "appConfigId.application")
    @Mapping(target = "name", source = "appConfigId.name")
    @Mapping(target = "environment", source = "appConfigId.deploymentStage")
    AppConfigModel toModel(AppConfig appConfig);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "appConfigId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    AppConfig toEntity(CreateAppConfigCommand createAppConfigCommand);


    @Mapping(target = "version", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "appConfigId", ignore = true)
    @Mapping(target = "active", ignore = true)
    @BeanMapping
    void updateEntity(UpdateAppConfigCommand updateAppConfigCommand,
                      @MappingTarget AppConfig appConfig);
}
