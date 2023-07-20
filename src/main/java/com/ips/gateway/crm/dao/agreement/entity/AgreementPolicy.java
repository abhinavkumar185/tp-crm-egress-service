package com.ips.gateway.crm.dao.agreement.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AgreementPolicy {

    private String signatureFileName;
    private String createdBySignature;
    private Integer productId;
    private Integer costScheduleId;
    private String contractFileName;
    private String createdByProductContractMapping;
    private String opportunityId;


}
