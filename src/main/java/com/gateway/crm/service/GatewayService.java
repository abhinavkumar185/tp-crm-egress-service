package com.gateway.crm.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gateway.crm.service.dto.RequestData;
import com.gateway.crm.service.onboarding.dto.BusinessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class GatewayService {
    private static final Logger log = LoggerFactory.getLogger(GatewayService.class);

    @Autowired
    OnboardingGateway onboardingGateway;


    public JsonNode process(BusinessEvent event){
        ObjectNode requestPayload = new ObjectMapper().createObjectNode();
        //requestPayload.set("Data",event);
        requestPayload.set("Risk",new ObjectMapper().createObjectNode());
        requestPayload.set("Links",new ObjectMapper().createObjectNode());
        requestPayload.set("Meta",new ObjectMapper().createObjectNode());
        RequestData requestData = RequestData.build("/sync/details",
                addHeaders(new HashMap<>()),
                requestPayload);
        JsonNode jsonNode = onboardingGateway.exchange("post-operation", HttpMethod.POST, requestData);

        return jsonNode;
    }



    public Map<String,String> addHeaders(Map<String,String> requestHeaders){
        requestHeaders.put("x-fapi-epoch-millis",String.valueOf(System.currentTimeMillis()));
        requestHeaders.put("x-fapi-serviceVersion","1.0");
        requestHeaders.put("Content-Type","text/plain");
        return requestHeaders;
    }

}
