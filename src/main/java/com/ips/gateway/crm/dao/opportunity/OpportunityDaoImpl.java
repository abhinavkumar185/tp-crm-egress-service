package com.ips.gateway.crm.dao.opportunity;

import com.ips.gateway.crm.dao.opportunity.entity.Opportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OpportunityDaoImpl implements OpportunityDao {
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Override
    public Opportunity findById(Integer opportunityId){
        return opportunityRepository.findById(opportunityId);
    }
}
