package com.ips.gateway.crm.service.bankAccount;

import com.ips.gateway.crm.dao.bankaccount.entity.BankAccount;

import java.util.List;

public interface BankAccountService {
    List<BankAccount> findById(Integer opportunityId);
}
