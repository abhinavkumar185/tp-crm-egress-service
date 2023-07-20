package com.ips.gateway.crm.dao.opportunitysynclog;

import com.ips.gateway.crm.dao.opportunitysynclog.entity.OpportunitySyncLog;

public interface OpportunitySyncLogDao {
    OpportunitySyncLog findById(Integer opportunitySyncLogId);
    Integer updateById(Integer opportunitySyncLogId);

}
