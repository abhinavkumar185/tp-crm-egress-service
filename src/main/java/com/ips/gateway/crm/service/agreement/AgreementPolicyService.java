package com.ips.gateway.crm.service.agreement;

import com.ips.gateway.crm.dao.agreement.entity.AgreementPolicy;
import com.ips.gateway.crm.dao.bankaccount.entity.BankAccount;

import java.util.List;

public interface AgreementPolicyService {
    List<AgreementPolicy> findById(Integer opportunityId);
}
