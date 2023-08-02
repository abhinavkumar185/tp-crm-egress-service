package com.gateway.crm.service.kyc;

import com.gateway.crm.dao.kyc.opportunitykycupload.entity.OpportunityKycUpload;
import com.gateway.crm.dao.kyc.opportunitykycupload.OpportunityKycUploadDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OpportunityKycUploadServiceImpl implements OpportunityKycUploadService{
	
	@Autowired
	private OpportunityKycUploadDao opportunityKycUploadDao;
	
	@Override
	public List<OpportunityKycUpload> findByOpportunityId(Integer opportunityId) {
		return opportunityKycUploadDao.findByOpportunityId(opportunityId);
	}
}
