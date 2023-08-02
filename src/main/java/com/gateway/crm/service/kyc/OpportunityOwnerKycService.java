package com.gateway.crm.service.kyc;

import com.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;

import java.util.List;

public interface OpportunityOwnerKycService {
	
	List<OpportunityOwnerKyc> findByOpportunityId(Integer opportunityId);
	
}
