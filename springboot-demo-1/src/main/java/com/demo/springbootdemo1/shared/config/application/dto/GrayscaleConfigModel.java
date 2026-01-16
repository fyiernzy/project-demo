package com.demo.springbootdemo1.shared.config.application.dto;

import java.util.Set;

public record GrayscaleConfigModel(
    String module,
    String identifier,
    Boolean open,
    Double percentage,
    Set<String> whitelist,
    Set<String> blacklist
) {

}
