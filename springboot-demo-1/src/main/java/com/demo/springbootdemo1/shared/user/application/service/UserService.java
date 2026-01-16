package com.demo.springbootdemo1.shared.user.application.service;

import com.demo.springbootdemo1.shared.user.application.dto.CreateUserModel;
import com.demo.springbootdemo1.shared.user.application.dto.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {

    @NonNull UserModel createUser(@NotNull @Valid CreateUserModel createUserModel);

    @Nullable UserModel findUserById(@NotBlank String id);

    @Nullable UserModel findUserByUsername(@NotBlank String username);

    @NonNull Page<@NonNull UserModel> findUsers(@NotNull Pageable pageable);

    void deleteUser(String id);
}
