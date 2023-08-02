package com.gateway.crm.service.onboarding.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnboardingEvent extends ApplicationEvent {
    private JsonNode oldEvent;
    private JsonNode newEvent;
    private String actionType;

    public OnboardingEvent(Object oldEvent, Object newEvent, String actionType) {
        super(oldEvent);
        this.oldEvent = (JsonNode) oldEvent;
        this.newEvent = (JsonNode) newEvent;
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "OnboardingEvent{" +
                "oldEvent=" + oldEvent +
                '}';
    }
}
