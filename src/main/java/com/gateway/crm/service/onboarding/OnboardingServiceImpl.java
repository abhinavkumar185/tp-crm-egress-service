package com.gateway.crm.service.onboarding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateway.crm.dao.agreement.entity.AgreementPolicy;
import com.gateway.crm.dao.bankaccount.entity.BankAccount;
import com.gateway.crm.dao.contract.entity.OpportunityProductMapping;
import com.gateway.crm.dao.invitation.entity.Invitation;
import com.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;
import com.gateway.crm.dao.kyc.opportunitykycupload.entity.OpportunityKycUpload;
import com.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;
import com.gateway.crm.dao.operator.entity.Operator;
import com.gateway.crm.dao.opportunity.entity.Opportunity;
import com.gateway.crm.service.contract.ContractService;
import com.gateway.crm.service.kyc.OpportunityKycService;
import com.gateway.crm.service.kyc.OpportunityKycUploadService;
import com.gateway.crm.service.kyc.OpportunityOwnerKycService;
import com.gateway.crm.service.onboarding.dto.BusinessEvent;
import com.gateway.crm.service.onboarding.dto.OnboardingEvent;
import com.gateway.crm.service.onboarding.dto.OnboardingEventDto;
import com.gateway.crm.service.opportunity.OpportunityService;
import com.gateway.crm.service.opportunitySyncLog.opportunity.OpportunitySyncLogService;
import com.gateway.crm.service.ws.AcquiringGateway;
import com.gateway.crm.service.agreement.AgreementPolicyService;
import com.gateway.crm.service.bankAccount.BankAccountService;
import com.gateway.crm.service.invitation.InvitationService;
import com.gateway.crm.service.operator.OperatorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OnboardingServiceImpl implements OnboardingService{

    private final OperatorService operatorService;
    private final OpportunityService opportunityService;
    private final OpportunitySyncLogService opportunitySyncLogService;
    private final InvitationService invitationService;
    private final AcquiringGateway acquiringGateway;
    private final BankAccountService bankAccountService;
    private final AgreementPolicyService agreementPolicyService;
    private final ContractService contractService;
    private final OpportunityKycService opportunityKycService;
    private final OpportunityKycUploadService opportunityKycUploadService;
    private final OpportunityOwnerKycService opportunityOwnerKycService;

    @Autowired
    public OnboardingServiceImpl(OperatorService operatorService, OpportunityService opportunityService, OpportunitySyncLogService opportunitySyncLogService,
                                 InvitationService invitationService, AcquiringGateway acquiringGateway, BankAccountService bankAccountService, AgreementPolicyService agreementPolicyService,ContractService contractService,OpportunityKycService opportunityKycService, OpportunityKycUploadService opportunityKycUploadService, OpportunityOwnerKycService opportunityOwnerKycService) {
        this.operatorService = operatorService;
        this.opportunityService = opportunityService;
        this.opportunitySyncLogService = opportunitySyncLogService;
        this.invitationService = invitationService;
        this.acquiringGateway = acquiringGateway;
        this.bankAccountService = bankAccountService;
        this.agreementPolicyService = agreementPolicyService;
        this.opportunityKycService = opportunityKycService;
        this.opportunityKycUploadService = opportunityKycUploadService;
        this.opportunityOwnerKycService = opportunityOwnerKycService;
        this.contractService = contractService;
    }

    @Override
    public void createOpportunityOnboardedEventData(OnboardingEvent onboardingEvent) {
        try {
            log.info("createOpportunityOnboardedEventData event start -> " + onboardingEvent);
            BusinessEvent event = null;
            ObjectMapper objectMapper = new ObjectMapper();
            OnboardingEventDto onboardingEventDto = objectMapper.readValue(onboardingEvent.getNewEvent().toString(), OnboardingEventDto.class);
            Invitation invitation = invitationService.findById(onboardingEventDto.getInvitation_id(), onboardingEventDto.getId());
            Operator operator = operatorService.findById(onboardingEventDto.getCreated_by());
            if(onboardingEventDto.getOpportunity_id() != null) {
                Opportunity opportunity = opportunityService.findById(onboardingEventDto.getOpportunity_id());
                List<BankAccount> bankAccountList = bankAccountService.findById(onboardingEventDto.getOpportunity_id());
                List<AgreementPolicy> agreementPolicyList = agreementPolicyService.findById(onboardingEventDto.getOpportunity_id());
                List<OpportunityProductMapping> opportunityProductMappingList = contractService.findByOpportunityId(onboardingEventDto.getOpportunity_id());
//            CostSchedule costSchedule = contractService.findByOpportunityProductMappingId(onboardingEventDto.getOpportunityProductMappingId());
//            List<CostScheduleItem> costScheduleItemList = contractService.findByCostScheduleId(onboardingEventDto.getCostScheduleId());
                List<OpportunityKyc> opportunityKycList = opportunityKycService.findByOpportunityId(onboardingEventDto.getOpportunity_id());
                List<OpportunityKycUpload> opportunityKycUploadList = opportunityKycUploadService.findByOpportunityId(onboardingEventDto.getOpportunity_id());
                List<OpportunityOwnerKyc> opportunityOwnerKycList = opportunityOwnerKycService.findByOpportunityId(onboardingEventDto.getOpportunity_id());
                //operator.setUid();

                event = BusinessEvent.builder()
                        .invitation(invitation)
                        .opportunity(opportunity)
                        .opportunityProductMappingList(opportunityProductMappingList)
//                    .costSchedule(costSchedule)
//                    .costScheduleItemList(costScheduleItemList)
                        .bankAccount(bankAccountList)
                        .onboardedByOperator(operator)
                        .agreementPolicyList(agreementPolicyList)
                        .opportunityKycList(opportunityKycList)
                        .opportunityKycUploadList(opportunityKycUploadList)
                        .opportunityOwnerKycList(opportunityOwnerKycList)
                        .build();
            }
            else{
                event = BusinessEvent.builder()
                        .invitation(invitation)
                        .onboardedByOperator(operator)
                        .build();
            }
            log.error("event objectMapper json : "+ objectMapper.writeValueAsString(event));
            acquiringGateway.sendMessage(objectMapper.writeValueAsString(event));
            log.error("opportunitySyncLogService start : "+ onboardingEventDto.getId());
            opportunitySyncLogService.updateById(onboardingEventDto.getId());
            log.error("opportunitySyncLogService updated as done : "+ onboardingEventDto.getId());
        }
        catch (Exception e){
            log.error("Error while creating the onboarding object with event data :: {}", onboardingEvent.getNewEvent().toString());
            log.error("Error while creating the onboarding object with exception",e);
        }
    }
}
