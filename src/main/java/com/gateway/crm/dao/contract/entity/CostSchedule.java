package com.gateway.crm.dao.contract.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class CostSchedule {

    private Integer id;
    private Date effectiveFromDateTime;
    private Date effectiveToDateTime;
    private Integer countryId;
    private Integer costScheduleTypeId;
    private Integer usedAsTemplate;
    private Integer createdByOperatorId;

}
