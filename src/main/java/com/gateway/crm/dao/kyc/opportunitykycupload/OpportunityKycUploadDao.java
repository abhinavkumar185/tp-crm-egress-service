package com.gateway.crm.dao.kyc.opportunitykycupload;

import com.gateway.crm.dao.kyc.opportunitykycupload.entity.OpportunityKycUpload;

import java.util.List;

public interface OpportunityKycUploadDao {
	
	List<OpportunityKycUpload> findByOpportunityId(Integer opportunityId);
}
