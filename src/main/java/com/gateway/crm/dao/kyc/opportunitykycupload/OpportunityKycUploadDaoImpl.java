package com.gateway.crm.dao.kyc.opportunitykycupload;

import com.gateway.crm.dao.kyc.opportunitykycupload.entity.OpportunityKycUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpportunityKycUploadDaoImpl implements OpportunityKycUploadDao{
	
	@Autowired
	private OpportunityKycUploadRepository opportunityKycUploadRepository;
	
	@Override
	public List<OpportunityKycUpload> findByOpportunityId(Integer opportunityId) {
		return opportunityKycUploadRepository.findByOpportunityId(opportunityId);
	}
}
