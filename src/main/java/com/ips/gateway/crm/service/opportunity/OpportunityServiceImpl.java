package com.ips.gateway.crm.service.opportunity;

import com.ips.gateway.crm.dao.opportunity.OpportunityDao;
import com.ips.gateway.crm.dao.opportunity.entity.Opportunity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OpportunityServiceImpl implements OpportunityService{

    @Autowired
    private OpportunityDao opportunityDao;

    @Override
    public Opportunity findById(Integer opportunityId){
        return opportunityDao.findById(opportunityId);
    }
}
