package com.gateway.crm.service.operator;

import com.gateway.crm.dao.operator.OperatorDao;
import com.gateway.crm.dao.operator.entity.Operator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OperatorServiceImpl implements OperatorService{

    private final OperatorDao operatorDao;

    @Autowired
    public OperatorServiceImpl(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

    @Override
    public Operator findById(Integer operatorId){
        return operatorDao.findById(operatorId);
    }
}
