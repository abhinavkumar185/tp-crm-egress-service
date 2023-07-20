package com.ips.gateway.crm.service.kyc;

import com.ips.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;

import java.util.List;

public interface OpportunityOwnerKycService {
	
	List<OpportunityOwnerKyc> findByOpportunityId(Integer opportunityId);
	
}
