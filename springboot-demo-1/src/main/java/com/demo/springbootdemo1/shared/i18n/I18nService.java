package com.demo.springbootdemo1.shared.i18n;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.Nullable;
import org.springframework.validation.annotation.Validated;

import java.util.Locale;

@Validated
public interface I18nService {

    @Nullable String resolveMessage(@NotEmpty String code, @NotNull Locale locale);

}
