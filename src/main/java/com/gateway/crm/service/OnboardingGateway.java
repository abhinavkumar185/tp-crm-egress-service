package com.gateway.crm.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.gateway.crm.error.Error;
import com.gateway.crm.error.ServiceException;
import com.gateway.crm.service.dto.RequestData;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class OnboardingGateway {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    public int exchange(String operation, HttpMethod method, RequestData requestData){
        log.error("exchange start : " + operation);
        ResponseEntity<?> result = null;
        int returnValue = -1;
        try{
            String encrypted = "";
            log.error("PLAIN PAYLOAD : " +requestData.getPayload() + " : ingressEndpoint URL : " + env.getProperty("ingressEndpoint"));
            String url = env.getProperty("ingressEndpoint")+requestData.getEndPoint();
            //String url = "http://10.0.30.166:9042"+requestData.getEndPoint();
            HttpEntity<String> entity = new HttpEntity<String>(requestData.getPayload().toString(), requestData.getHeader());
            log.error("URL : "+url + " : method : " + method + " : entity : " + entity);
            result = restTemplate
                    .exchange(url,method, entity, String.class );

            log.error("result : " + result);
            log.error("============================ HTTP Response Begin ============================\n" +
                            "Status code  : " + result.getStatusCode().value() + "\n" +
                            "Status text  : " + result.getStatusCode().getReasonPhrase() + "\n" +
                            "Headers      : " + result.getHeaders() + "\n" +
                            "Response body: " + result.getBody() + "\n" +
                            "============================= HTTP Response End ============================="
            );

            if(!result.getStatusCode().is2xxSuccessful()){
                if(result.hasBody()){
                    JsonNode node = null;
                    log.error("Call to crm ingress gateway failed : UID - {} : URI - {} : RESPONSE : {}",
                            requestData.getHeader().get("x-fapi-uuid").toString(),requestData.getURI(), node);
                }
                throw new ServiceException(operation, Error.SERVER_ERROR);
            }

            if(!result.hasBody())
                throw new ServiceException(operation, Error.BANK_SESSION_EXPIRED);

            returnValue = result.getStatusCode().value();
            log.error("returnValue : "+returnValue);
        }
        catch (Exception e){
            log.error("Exception in OnboardingGateway : ", e);
            //throw new ServiceException(operation, Error.SERVER_ERROR);
        }
        return returnValue;
    }

}
