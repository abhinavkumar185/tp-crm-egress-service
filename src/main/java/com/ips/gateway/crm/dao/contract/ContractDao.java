package com.ips.gateway.crm.dao.contract;

import com.ips.gateway.crm.dao.contract.entity.CostSchedule;
import com.ips.gateway.crm.dao.contract.entity.CostScheduleItem;
import com.ips.gateway.crm.dao.contract.entity.OpportunityProductMapping;

import java.util.List;

public interface ContractDao {

    List<OpportunityProductMapping> findByOpportunityId(Integer opportunityId);

    CostSchedule findByOpportunityProductMappingId(Integer opportunityProductMappingId);

    List<CostScheduleItem> findByCostScheduleId(Integer costScheduleId);
}
