package com.demo.springbootdemo1.shared.user.application.mapper;

import com.demo.springbootdemo1.shared.user.application.dto.CreateUserModel;
import com.demo.springbootdemo1.shared.user.application.dto.UserModel;
import com.demo.springbootdemo1.shared.user.domain.User;
import com.demo.springbootdemo1.config.mapstruct.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface UserMapper {

    UserModel toModel(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    User toEntity(CreateUserModel request);
}
