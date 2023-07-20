package com.ips.gateway.crm.dao.kyc.opportunityownerkyc;

import com.ips.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OpportunityOwnerKycDaoImpl implements OpportunityOwnerKycDao{
	
	@Autowired
	private OpportunityOwnerKycRepository opportunityOwnerKycRepository;
	
	@Override
	public List<OpportunityOwnerKyc> findByOpportunityId(Integer opportunityId) {
		return opportunityOwnerKycRepository.findByOpportunityId(opportunityId);
	}
}
