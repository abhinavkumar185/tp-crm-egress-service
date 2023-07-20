package com.ips.gateway.crm.service.operator;

import com.ips.gateway.crm.dao.operator.entity.Operator;

public interface OperatorService {
    Operator findById(Integer operatorId);
}
