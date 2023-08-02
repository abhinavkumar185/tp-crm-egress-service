package com.gateway.crm.service.onboarding.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = OnboardingEventDto.OnboardingEventDtoBuilder.class)
public class OnboardingEventDto {
    
    private Integer id;
    private Integer opportunity_id;
    private Integer invitation_id;
    private Integer active_status_id;
    private String created_on;
    private Integer created_by;
    private String last_updated_on;
    private String last_updated_by;
    private Integer batch_entry_status_id;
    
    
    private Integer invitationId;
    private Integer opportunityId;
    private Integer initiatedByOperatorId;
    private Integer opportunityProductMappingId;
    private Integer costScheduleId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class OnboardingEventDtoBuilder {
        // constructor, setter/getter and builder methods are auto-generated
    }
}
