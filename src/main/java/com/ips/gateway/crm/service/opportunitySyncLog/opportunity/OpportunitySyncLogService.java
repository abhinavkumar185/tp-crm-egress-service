package com.ips.gateway.crm.service.opportunitySyncLog.opportunity;

import com.ips.gateway.crm.dao.opportunitysynclog.entity.OpportunitySyncLog;

public interface OpportunitySyncLogService {
    OpportunitySyncLog findById(Integer opportunitySyncLogId);
    Integer updateById(Integer opportunitySyncLogId);
}
