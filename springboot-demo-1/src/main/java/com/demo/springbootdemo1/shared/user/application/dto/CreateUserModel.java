package com.demo.springbootdemo1.shared.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserModel(
    @NotBlank String username,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email @NotBlank String email,
    @NotBlank String password
) {

}
