package com.ips.gateway.crm.dao.kyc.opportunitykyc;

import com.ips.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;

import java.util.List;

public interface OpportunityKycDao {
	
	List<OpportunityKyc> findByOpportunityId(Integer opportunityId);
}
