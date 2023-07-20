package com.ips.gateway.crm.dao.opportunity.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder(toBuilder = true)
public class Opportunity {
    
    //opportunity
    private String businessName;
    private String createdBy;
    private String lastUpdatedBy;
    private String journeyStagesId;
    
    //opportunity detail
    private String mobileNumber;
    private String contactName;
    private Integer opportunityId;
    private String assignTo;
    private String uuid;
    private Integer entityTypeId;
    private Integer roleNestingId;

    //genius portal related data - opportunity detail
    private Integer businessTypeId;
    private String companyRegistrationNumber;
    private BigDecimal refundVolume;
    private BigDecimal chargeback;
    private BigDecimal projectedCardSales;
    private BigDecimal cardSales;
    private BigDecimal totalSales;
    private Integer months;
    private Integer years;
    private String officialWebSiteUrl;
    private String webSiteUrl;
}
