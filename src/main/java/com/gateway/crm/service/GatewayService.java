package com.gateway.crm.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gateway.crm.service.dto.RequestData;
import com.gateway.crm.service.onboarding.dto.BusinessEvent;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class GatewayService {
    @Autowired
    OnboardingGateway onboardingGateway;

    public int process(BusinessEvent event){
        log.error("process start : "+event);
        //ObjectNode requestPayload = new ObjectMapper().createObjectNode();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode eventNode = mapper.convertValue(event, JsonNode.class);
       // requestPayload.set("Data",eventNode);
        /*requestPayload.set("Risk",new ObjectMapper().createObjectNode());
        requestPayload.set("Links",new ObjectMapper().createObjectNode());
        requestPayload.set("Meta",new ObjectMapper().createObjectNode());*/
        RequestData requestData = RequestData.build("/sync/details",
                addHeaders(new HashMap<>()),
                eventNode);
        log.error("requestData values : "+requestData);
        int statusCode = onboardingGateway.exchange("post-operation", HttpMethod.POST, requestData);

        return statusCode;
    }



    public Map<String,String> addHeaders(Map<String,String> requestHeaders){
        requestHeaders.put("x-fapi-epoch-millis",String.valueOf(System.currentTimeMillis()));
        requestHeaders.put("x-fapi-serviceVersion","1.0");
        requestHeaders.put("Content-Type","application/json");
        return requestHeaders;
    }

}
