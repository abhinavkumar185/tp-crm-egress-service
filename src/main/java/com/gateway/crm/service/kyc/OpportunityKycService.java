package com.gateway.crm.service.kyc;

import com.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;

import java.util.List;

public interface OpportunityKycService {
	
	List<OpportunityKyc> findByOpportunityId(Integer opportunityId);
}
