package com.gateway.crm.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.gateway.crm.error.Error;
import com.gateway.crm.error.ServiceException;
import com.gateway.crm.service.dto.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OnboardingGateway {

    private static final Logger log = LoggerFactory.getLogger(OnboardingGateway.class);

    @Autowired
    private RestTemplate restTemplate;

    public JsonNode exchange(String operation, HttpMethod method, RequestData requestData){
        try{
            String encrypted = "";
            log.info("PLAIN PAYLOAD : {}, \n ENCRYPTED PAYLOAD : {}",requestData.getPayload(), encrypted);

            //String url = applicationProperties.getIngressEndpoint()+requestData.getEndPoint();
            String url = "http://10.0.30.166:9042"+requestData.getEndPoint();
            HttpEntity<String> entity = new HttpEntity<>(encrypted, requestData.getHeader());
            ResponseEntity<?> result = restTemplate
                    .exchange(url,method, entity, String.class );

            log.info("============================ HTTP Response Begin ============================\n" +
                            "Status code  : " + result.getStatusCode().value() + "\n" +
                            "Status text  : " + result.getStatusCode().getReasonPhrase() + "\n" +
                            "Headers      : " + result.getHeaders() + "\n" +
                            "Response body: " + result.getBody() + "\n" +
                            "============================= HTTP Response End ============================="
            );

            if(!result.getStatusCode().is2xxSuccessful()){
                if(result.hasBody()){
                    JsonNode node = null;
                    log.error("Call to axis gateway failed : UID - {} : URI - {} : RESPONSE : {}",
                            requestData.getHeader().get("x-fapi-uuid").toString(),requestData.getURI(), node);
                }
                throw new ServiceException(operation, Error.SERVER_ERROR);
            }

            if(!result.hasBody())
                throw new ServiceException(operation, Error.BANK_SESSION_EXPIRED);

            //return jwe.decryptResponseBody(operation, (String)result.getBody());
            return null;
        }
        catch (Exception e){
            throw new ServiceException(operation, Error.SERVER_ERROR);
        }
    }

}
