package com.gateway.crm.dao.kyc.opportunityownerkyc;

import com.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;

import java.util.List;

public interface OpportunityOwnerKycDao {
	
	List<OpportunityOwnerKyc> findByOpportunityId(Integer opportunityId);
}
