package com.gateway.crm.config.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gateway.crm.config.LowercaseEnum;

public enum HttpRequestType implements LowercaseEnum {
    GET,
    POST,
    PUT,
    DELETE,
    PATCH;

    @JsonCreator
    public static HttpRequestType deserialize(String name) {
        return LowercaseEnum.deserialize(HttpRequestType.class, name);
    }

    public static HttpRequestType of(String name) {
        return LowercaseEnum.forValue(HttpRequestType.class, name);
    }
}