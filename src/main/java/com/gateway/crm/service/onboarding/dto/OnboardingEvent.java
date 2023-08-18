package com.gateway.crm.service.onboarding.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class OnboardingEvent {
    private JsonNode oldEvent;
    private JsonNode newEvent;
    private String actionType;

    public OnboardingEvent(Object oldEvent, Object newEvent, String actionType) {
        this.oldEvent = (JsonNode) oldEvent;
        this.newEvent = (JsonNode) newEvent;
        this.actionType = actionType;
    }
}
