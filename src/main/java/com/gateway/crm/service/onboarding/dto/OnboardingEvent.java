package com.gateway.crm.service.onboarding.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonDeserialize(builder = OnboardingEvent.OnboardingEventBuilder.class)
public class OnboardingEvent {
    @JsonPOJOBuilder(withPrefix = "")
    public static class OnboardingEventBuilder {}

    private JsonNode oldEvent;
    private JsonNode newEvent;
    private String actionType;
}
