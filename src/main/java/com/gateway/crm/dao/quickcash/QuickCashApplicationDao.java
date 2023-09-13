package com.gateway.crm.dao.quickcash;

import com.gateway.crm.dao.quickcash.entity.QuickCashApplicationDetail;

import java.util.List;

public interface QuickCashApplicationDao {

    List<QuickCashApplicationDetail> findByOpportunityId(Integer opportunityId);
}
