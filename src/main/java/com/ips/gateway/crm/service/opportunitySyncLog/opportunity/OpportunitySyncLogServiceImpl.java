package com.ips.gateway.crm.service.opportunitySyncLog.opportunity;

import com.ips.gateway.crm.dao.opportunitysynclog.OpportunitySyncLogDao;
import com.ips.gateway.crm.dao.opportunitysynclog.entity.OpportunitySyncLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OpportunitySyncLogServiceImpl implements OpportunitySyncLogService {
    @Autowired
    private OpportunitySyncLogDao opportunityDao;

    @Override
    public OpportunitySyncLog findById(Integer opportunitySyncLogId){
        return opportunityDao.findById(opportunitySyncLogId);
    }

    @Override
    public Integer updateById(Integer opportunitySyncLogId){
        return opportunityDao.updateById(opportunitySyncLogId);
    }
}
