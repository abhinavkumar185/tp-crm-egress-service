package com.gateway.crm.service.opportunity;

import com.gateway.crm.dao.opportunity.entity.Opportunity;

public interface OpportunityService {
    Opportunity findById(Integer opportunityId);
}
