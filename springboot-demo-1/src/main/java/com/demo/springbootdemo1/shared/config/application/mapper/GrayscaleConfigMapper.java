package com.demo.springbootdemo1.shared.config.application.mapper;

import com.demo.springbootdemo1.config.mapstruct.GlobalMapperConfig;
import com.demo.springbootdemo1.shared.config.application.dto.CreateGrayscaleConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.GrayscaleConfigModel;
import com.demo.springbootdemo1.shared.config.application.dto.UpdateGrayscaleConfigModel;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface GrayscaleConfigMapper {

    GrayscaleConfigModel toModel(CreateGrayscaleConfigModel createGrayscaleConfigModel);

    GrayscaleConfigModel toModel(UpdateGrayscaleConfigModel updateGrayscaleConfigModel);
}
