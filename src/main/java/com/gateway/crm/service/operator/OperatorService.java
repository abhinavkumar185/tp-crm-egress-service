package com.gateway.crm.service.operator;

import com.gateway.crm.dao.operator.entity.Operator;

public interface OperatorService {
    Operator findById(Integer operatorId);
}
