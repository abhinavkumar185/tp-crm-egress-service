package com.ips.gateway.crm.service.kyc;

import com.ips.gateway.crm.dao.kyc.opportunitykycupload.entity.OpportunityKycUpload;

import java.util.List;

public interface OpportunityKycUploadService {
	
	List<OpportunityKycUpload> findByOpportunityId(Integer opportunityId);
	
}
