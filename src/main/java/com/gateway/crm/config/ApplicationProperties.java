package com.gateway.crm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@ConfigurationProperties(prefix = "application") // supports JSR-303 bean validation
@Validated
public class ApplicationProperties {

    /*@NotNull
    private String ingressEndpoint;*/


}
