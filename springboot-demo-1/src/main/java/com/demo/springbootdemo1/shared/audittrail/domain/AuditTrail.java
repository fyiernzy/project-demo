package com.demo.springbootdemo1.shared.audittrail.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;

@Table
@Entity
public class AuditTrail {

    @Id
    @UuidGenerator
    private String id;

    private ZonedDateTime createdAt;
}
