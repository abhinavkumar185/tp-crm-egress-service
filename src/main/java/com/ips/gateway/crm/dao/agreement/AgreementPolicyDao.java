package com.ips.gateway.crm.dao.agreement;

import com.ips.gateway.crm.dao.agreement.entity.AgreementPolicy;

import java.util.List;

public interface AgreementPolicyDao {
    List<AgreementPolicy> findById(Integer opportunityId);
}
