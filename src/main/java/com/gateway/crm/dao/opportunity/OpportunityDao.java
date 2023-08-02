package com.gateway.crm.dao.opportunity;

import com.gateway.crm.dao.opportunity.entity.Opportunity;

public interface OpportunityDao {
    Opportunity findById(Integer opportunityId);
}
