package com.gateway.crm.service.opportunitySyncLog.opportunity;

import com.gateway.crm.dao.opportunitysynclog.entity.OpportunitySyncLog;

public interface OpportunitySyncLogService {
    OpportunitySyncLog findById(Integer opportunitySyncLogId);
    Integer updateById(Integer opportunitySyncLogId);
}
