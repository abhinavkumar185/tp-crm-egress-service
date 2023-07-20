package com.ips.gateway.crm.service.operator;

import com.ips.gateway.crm.dao.operator.entity.Operator;
import com.ips.gateway.crm.dao.opportunity.OpportunityDao;
import com.ips.gateway.crm.service.ws.AcquiringGateway;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Log4j2
@Component
public class OperatorEventListener implements ApplicationListener<OperatorEvent> {
    @Autowired
    private AcquiringGateway acquiringGateway;

    @Autowired
    private OperatorService operatorService;

    @Override
    public void onApplicationEvent(OperatorEvent event){
        //DO the async processing here
        log.info("OP event listener -> {}", event);
        Operator resultSet = operatorService.findById(1);
    }
}
