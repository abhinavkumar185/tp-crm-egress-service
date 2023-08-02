package com.gateway.crm.service.bankAccount;

import com.gateway.crm.dao.bankaccount.entity.BankAccount;

import java.util.List;

public interface BankAccountService {
    List<BankAccount> findById(Integer opportunityId);
}
