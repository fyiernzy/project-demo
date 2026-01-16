package com.demo.springbootdemo1.shared.i18n;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class I18nServiceImpl implements I18nService {

    private final MessageSource messageSource;

    @Override
    public @Nullable String resolveMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }
}
