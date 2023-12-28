package com.gateway.crm.endpoint.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value // make DTO class immutable as it is passed around
@Builder
@JsonDeserialize(builder = PayloadResponse.PayloadResponseBuilder.class)
public class PayloadResponse {

    private String response;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PayloadResponseBuilder {
        // Lombok will add constructor, setters, build method
    }
}
