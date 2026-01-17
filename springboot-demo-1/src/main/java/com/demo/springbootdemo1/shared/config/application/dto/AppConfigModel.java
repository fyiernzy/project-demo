package com.demo.springbootdemo1.shared.config.application.dto;

public record AppConfigModel(
    String application,
    String name,
    String value,
    String description,
    boolean active
) {

}
