package com.gateway.crm.service.agreement;

import com.gateway.crm.dao.agreement.entity.AgreementPolicy;

import java.util.List;

public interface AgreementPolicyService {
    List<AgreementPolicy> findById(Integer opportunityId);
}
