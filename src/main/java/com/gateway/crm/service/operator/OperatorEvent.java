package com.gateway.crm.service.operator;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.ApplicationEvent;

public class OperatorEvent extends ApplicationEvent {
    private JsonNode object;

    public OperatorEvent(Object source) {
        super(source);
        this.object = (JsonNode) source;
    }

    @Override
    public String toString() {
        return "OperatorEvent{" +
                "object=" + object +
                '}';
    }
}
