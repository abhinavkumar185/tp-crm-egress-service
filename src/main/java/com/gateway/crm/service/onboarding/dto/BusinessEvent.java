package com.gateway.crm.service.onboarding.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;
import com.gateway.crm.dao.kyc.opportunitykycupload.entity.OpportunityKycUpload;
import com.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;
import com.gateway.crm.dao.agreement.entity.AgreementPolicy;
import com.gateway.crm.dao.bankaccount.entity.BankAccount;
import com.gateway.crm.dao.contract.entity.CostSchedule;
import com.gateway.crm.dao.contract.entity.CostScheduleItem;
import com.gateway.crm.dao.contract.entity.OpportunityProductMapping;
import com.gateway.crm.dao.invitation.entity.Invitation;
import com.gateway.crm.dao.operator.entity.Operator;
import com.gateway.crm.dao.opportunity.entity.Opportunity;
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
