package com.ips.gateway.crm.dao.contract.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Contract {

    private Integer id;
    private Integer costScheduleId;
    private String name;
    private Integer countryId;
    private Date createdOn;
    private Date deactivatedOn;
    private Integer createdByOperatorId;
    private Integer deactivatedByOperatorId;
    private Integer costScheduleTypeId;
    private Integer usedAsTemplate;
    private Date effectiveFromDateTime;
    private Date effectiveToDate;
    private Integer opportunityId;
    private Integer productId;
    private Integer statusId;
    private Integer bankId;
    private Integer pricingPlanTypeId;
    private Integer costScheduleItemTypeId;
    private BigDecimal price;
    private BigDecimal secondaryPrice;
    private BigDecimal pricePercent;
    private Integer count;
    private String unit;
}
