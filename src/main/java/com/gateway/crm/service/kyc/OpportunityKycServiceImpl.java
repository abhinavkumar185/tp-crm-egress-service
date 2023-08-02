package com.gateway.crm.service.kyc;

import com.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;
import com.gateway.crm.dao.kyc.opportunitykyc.OpportunityKycDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OpportunityKycServiceImpl implements OpportunityKycService{
	
	@Autowired
	private OpportunityKycDao opportunityKycDao;
	
	@Override
	public List<OpportunityKyc> findByOpportunityId(Integer opportunityId) {
		return opportunityKycDao.findByOpportunityId(opportunityId);
	}
}
