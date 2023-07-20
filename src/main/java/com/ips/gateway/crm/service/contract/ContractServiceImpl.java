package com.ips.gateway.crm.service.contract;

import com.ips.gateway.crm.dao.contract.ContractDao;
import com.ips.gateway.crm.dao.contract.entity.CostSchedule;
import com.ips.gateway.crm.dao.contract.entity.CostScheduleItem;
import com.ips.gateway.crm.dao.contract.entity.OpportunityProductMapping;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ContractServiceImpl implements ContractService{
    @Autowired
    private ContractDao contractDao;

    @Override
    public List<OpportunityProductMapping> findByOpportunityId(Integer opportunityId) {
        return contractDao.findByOpportunityId(opportunityId);
    }

    @Override
    public CostSchedule findByOpportunityProductMappingId(Integer opportunityProductMappingId) {
        return contractDao.findByOpportunityProductMappingId(opportunityProductMappingId);
    }

    @Override
    public List<CostScheduleItem> findByCostScheduleId(Integer costScheduleId) {
        return contractDao.findByCostScheduleId(costScheduleId);
    }
}
