package com.ips.gateway.crm.service.opportunity;

import com.ips.gateway.crm.dao.opportunity.entity.Opportunity;

public interface OpportunityService {
    Opportunity findById(Integer opportunityId);
}
