package com.gateway.crm.service.quickcash;

import com.gateway.crm.dao.quickcash.entity.QuickCashApplicationDetail;

import java.util.List;

public interface QuickCashService {

    List<QuickCashApplicationDetail>  findByOpportunityId(Integer opportunityId);
}
