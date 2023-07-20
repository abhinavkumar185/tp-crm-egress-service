package com.ips.gateway.crm.dao.kyc.opportunitykyc;

import com.ips.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpportunityKycDaoImpl implements OpportunityKycDao{
	
	@Autowired
	private OpportunityKycRepository opportunityKycRepository;
	
	@Override
	public List<OpportunityKyc> findByOpportunityId(Integer opportunityId) {
		return opportunityKycRepository.findByOpportunityId(opportunityId);
	}
}
