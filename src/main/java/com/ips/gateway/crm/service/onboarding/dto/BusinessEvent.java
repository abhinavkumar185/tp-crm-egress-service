package com.ips.gateway.crm.service.onboarding.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ips.gateway.crm.dao.agreement.entity.AgreementPolicy;
import com.ips.gateway.crm.dao.bankaccount.entity.BankAccount;
import com.ips.gateway.crm.dao.contract.entity.CostSchedule;
import com.ips.gateway.crm.dao.contract.entity.CostScheduleItem;
import com.ips.gateway.crm.dao.contract.entity.OpportunityProductMapping;
import com.ips.gateway.crm.dao.invitation.entity.Invitation;
import com.ips.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;
import com.ips.gateway.crm.dao.kyc.opportunitykycupload.entity.OpportunityKycUpload;
import com.ips.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;
import com.ips.gateway.crm.dao.operator.entity.Operator;
import com.ips.gateway.crm.dao.opportunity.entity.Opportunity;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = BusinessEvent.BusinessEventBuilder.class)
public class BusinessEvent {
    private Opportunity opportunity;
    private Invitation invitation;
    private Operator onboardedByOperator;
    private List<OpportunityProductMapping> opportunityProductMappingList;
    private CostSchedule costSchedule;
    private List<CostScheduleItem> costScheduleItemList;
    private List<OpportunityKyc> opportunityKycList;
    private List<OpportunityOwnerKyc> opportunityOwnerKycList;
    private List<OpportunityKycUpload> opportunityKycUploadList;
    private List<BankAccount> bankAccount;
    private List<AgreementPolicy> agreementPolicyList;
}
