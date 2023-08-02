package com.gateway.crm.dao.contract.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpportunityProductMapping {

    private Integer id;
    private Integer opportunityId;
    private Integer productId;
    private Integer bankId;
    private Integer pricingPlanTypeId;

}
