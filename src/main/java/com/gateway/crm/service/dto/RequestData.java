package com.gateway.crm.service.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Value
@Builder
@JsonDeserialize(builder = RequestData.RequestDataBuilder.class)
public class RequestData implements BaseObject{
    private String endPoint;
    private JsonNode payload;
    private MultiValueMap<String,String> header;

    @Override
    public String getURI() {
        return endPoint;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class RequestDataBuilder {
        // Lombok will add constructor, setters, build method
    }

    public static RequestData build(String endPoint, Map<String,String> header, JsonNode payload){
        MultiValueMap map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : header.entrySet()) {
            map.set(entry.getKey(),entry.getValue());
        }
        return RequestData.builder()
                .endPoint(endPoint)
                .payload(payload)
                .header(map)
                .build();
    }
}
