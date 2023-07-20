package com.ips.gateway.crm.dao.operator;

import com.ips.gateway.crm.dao.operator.entity.Operator;

public interface OperatorDao {
    Operator findById(Integer operatorId);
}
