package com.ips.gateway.crm.service.kyc;

import com.ips.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;

import java.util.List;

public interface OpportunityKycService {
	
	List<OpportunityKyc> findByOpportunityId(Integer opportunityId);
}
