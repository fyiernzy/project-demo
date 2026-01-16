package com.demo.springbootdemo1.shared.user.domain;

import com.demo.springbootdemo1.shared.audittrail.domain.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
@Table(name = "users")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class User extends Auditable {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
}
