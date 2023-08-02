package com.gateway.crm.error;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {
    private final String operation;
    private final Error error;

    public ServiceException(String operation, Error error) {
        super(error.getDescription());
        this.operation = operation;
        this.error = error;
    }
}