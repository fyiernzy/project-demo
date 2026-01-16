package com.demo.springbootdemo1.shared.audittrail.domain;

import com.demo.springbootdemo1.shared.audittrail.application.CustomRevisionListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Data
@Entity
@RevisionEntity(CustomRevisionListener.class)
public class CustomRevisionInfo {

    @Id
    @UuidGenerator
    @RevisionNumber
    private Long id;

    @RevisionTimestamp
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "username")
    private String username;
}
