package com.ips.gateway.crm.dao.bankaccount;

import com.ips.gateway.crm.dao.bankaccount.entity.BankAccount;

import java.util.List;

public interface BankAccountDao {
    List<BankAccount> findById(Integer opportunityId);
}
