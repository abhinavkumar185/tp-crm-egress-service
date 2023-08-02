package com.gateway.crm.service.kyc;

import com.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;
import com.gateway.crm.dao.kyc.opportunityownerkyc.OpportunityOwnerKycDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OpportunityOwnerKycServiceImpl implements OpportunityOwnerKycService{
	
	@Autowired
	private OpportunityOwnerKycDao opportunityOwnerKycDao;
	
	@Override
	public List<OpportunityOwnerKyc> findByOpportunityId(Integer opportunityId) {
		return opportunityOwnerKycDao.findByOpportunityId(opportunityId);
	}
}
