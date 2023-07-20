package com.ips.gateway.crm.service.ws;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Log4j2
@Service
public class AcquiringGateway {
    @Autowired
    @Qualifier(value = "crmProducer")
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send("pg.public.business_event", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.debug("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.debug("Unable to send message=["
                        + message + "] due to : " , ex);
            }
        });
    }
}
