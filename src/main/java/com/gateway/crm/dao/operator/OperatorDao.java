package com.gateway.crm.dao.operator;

import com.gateway.crm.dao.operator.entity.Operator;

public interface OperatorDao {
    Operator findById(Integer operatorId);
}
