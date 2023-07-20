package com.ips.gateway.crm.service.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ips.gateway.crm.service.onboarding.dto.OnboardingEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class OnboardingListener {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    //table "pg.public.onboarding_event"
    @KafkaListener(topics = "pg.public.opportunity_sync_log", groupId = "f_group_id",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        log.info("Received Message on topic {} in group - f_group-id: {}" ,topic, message);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode actualObj = mapper.readTree(message);
            OnboardingEvent event = new OnboardingEvent(actualObj.get("payload").get("before"),actualObj.get("payload").get("after")
                    ,actualObj.get("payload").get("op").asText());
            applicationEventPublisher.publishEvent(event);
        } catch (JsonProcessingException e) {
            log.error("Exception for Message on topic {} in group - f_group-id: {} , :: EX -> " ,topic, message, e);
        }
    }
}
