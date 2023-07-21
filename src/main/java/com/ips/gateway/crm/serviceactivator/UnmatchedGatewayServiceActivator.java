package com.ips.gateway.crm.serviceactivator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UnmatchedGatewayServiceActivator {
    private static final Logger logger = LoggerFactory.getLogger(UnmatchedGatewayServiceActivator.class);

    public String processUnmatchedGateway(String payload) {
        logger.error("Gateway not matched");
        //TODO: something with the payload
        return payload;
    }
}
