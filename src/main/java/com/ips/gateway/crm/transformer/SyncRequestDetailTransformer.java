package com.ips.gateway.crm.transformer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ips.gateway.crm.service.onboarding.OnboardingService;
import com.ips.gateway.crm.service.onboarding.dto.OnboardingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SyncRequestDetailTransformer {
    private static final Logger logger = LoggerFactory
            .getLogger(SyncRequestDetailTransformer.class);

    @Autowired
    private OnboardingService onboardingService;

    /**
     * Takes encrypted data field from request
     * and populates card details
     * before request is processed further.
     */
    public String transform(
            @Payload
            String payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonRequest = mapper
                .readValue(payload, JsonNode.class);
        ObjectNode oReq = (ObjectNode) jsonRequest;

        OnboardingEvent event = mapper
                .readValue(payload, OnboardingEvent.class);

        logger.info("OB event listener -> {}", event);
        if(event.getActionType().equalsIgnoreCase("c")){
            onboardingService.createOpportunityOnboardedEventData(event);
        }

        if(event.getActionType().equalsIgnoreCase("u")){
            //TODO: update events on KYC update should be called
        }
        oReq.put("response","Done");
        return oReq.toString();
    }

}
