package com.demo.springbootdemo1.shared.config.domain.repository;

import com.demo.springbootdemo1.shared.config.domain.entity.AppConfig;
import com.demo.springbootdemo1.shared.config.domain.entity.AppConfigId;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppConfigRepository extends
    JpaRepository<@NonNull AppConfig, @NonNull AppConfigId> {

}
