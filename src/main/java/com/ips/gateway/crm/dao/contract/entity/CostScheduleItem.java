package com.ips.gateway.crm.dao.contract.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder(toBuilder = true)
public class CostScheduleItem {

    private Integer createdByOperatorId;
    private Integer countryId;
    private Integer costScheduleItemTypeId;
    private Date createdDateTime;
    private BigDecimal price;
    private BigDecimal secondaryPrice;
    private BigDecimal pricePercent;
    private Integer count;
    private Integer telecomRateId;
    private Integer maxCount;
    private Integer unit;
    private Integer currencyCode;
    private Integer taxId;
    private Boolean taxPayable;

}
