package com.gateway.crm.dao.agreement;

import com.gateway.crm.dao.agreement.entity.AgreementPolicy;

import java.util.List;

public interface AgreementPolicyDao {
    List<AgreementPolicy> findById(Integer opportunityId);
}
