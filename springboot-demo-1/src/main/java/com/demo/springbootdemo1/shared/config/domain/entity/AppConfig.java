package com.demo.springbootdemo1.shared.config.domain.entity;

import com.demo.springbootdemo1.shared.audittrail.domain.Auditable;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "app_config")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class AppConfig extends Auditable {

    @EmbeddedId
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false, updatable = false)
    private AppConfigId id;

    @Column(name = "value", nullable = false)
    private String value;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Version
    @Column(name = "version")
    private Long version;
}
