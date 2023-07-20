package com.ips.gateway.crm.dao.contract;

import com.ips.gateway.crm.dao.contract.entity.CostSchedule;
import com.ips.gateway.crm.dao.contract.entity.CostScheduleItem;
import com.ips.gateway.crm.dao.contract.entity.OpportunityProductMapping;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
@Log4j2
public class ContractDaoImpl implements ContractDao{

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<OpportunityProductMapping> findByOpportunityId(Integer opportunityId) {
        return contractRepository.findByOpportunityId(opportunityId);
    }

    @Override
    public CostSchedule findByOpportunityProductMappingId(Integer opportunityProductMappingId) {
        return contractRepository.findByOpportunityProductMappingId(opportunityProductMappingId);
    }

    @Override
    public List<CostScheduleItem> findByCostScheduleId(Integer costScheduleId) {
        return contractRepository.findByCostScheduleId(costScheduleId);
    }
}
