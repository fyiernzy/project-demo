package com.demo.springbootdemo1.shared.config.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record AppConfigId(
    @Column(name = "application", nullable = false)
    String application,

    @Column(name = "module", nullable = false)
    String module,

    @Column(name = "name", nullable = false)
    String name
) {

}
