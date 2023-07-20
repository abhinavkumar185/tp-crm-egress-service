package com.ips.gateway.crm.dao.opportunity;

import com.ips.gateway.crm.dao.opportunity.entity.Opportunity;

public interface OpportunityDao {
    Opportunity findById(Integer opportunityId);
}
