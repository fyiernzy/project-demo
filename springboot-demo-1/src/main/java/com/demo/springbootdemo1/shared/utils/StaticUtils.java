package com.demo.springbootdemo1.shared.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StaticUtils {

    public static ObjectMapper INSTANCE = new ObjectMapper();

    public static ObjectMapper objectMapper() {
        return INSTANCE;
    }
}
