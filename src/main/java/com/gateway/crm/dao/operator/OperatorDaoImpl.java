package com.gateway.crm.dao.operator;

import com.gateway.crm.dao.operator.entity.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperatorDaoImpl implements OperatorDao{
    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public Operator findById(Integer operatorId){
        return operatorRepository.getOperator(operatorId);
    }
}
