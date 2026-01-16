package com.demo.springbootdemo1.shared.user.application.dto;

public record UserModel(
    String id,
    String username,
    String firstName,
    String lastName,
    String email
) {

}
