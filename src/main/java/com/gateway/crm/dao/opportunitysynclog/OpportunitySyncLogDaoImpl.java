package com.gateway.crm.dao.opportunitysynclog;

import com.gateway.crm.dao.opportunitysynclog.entity.OpportunitySyncLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OpportunitySyncLogDaoImpl implements OpportunitySyncLogDao {
    @Autowired
    private OpportunitySyncLogRepository operatorRepository;

    @Override
    public OpportunitySyncLog findById(Integer opportunitySyncLogId){
        return operatorRepository.getOpportunitySyncLog(opportunitySyncLogId);
    }

    @Override
    public Integer updateById(Integer opportunitySyncLogId){
        return operatorRepository.updateById(opportunitySyncLogId);
    }
}
