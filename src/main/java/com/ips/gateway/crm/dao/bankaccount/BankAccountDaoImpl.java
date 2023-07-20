package com.ips.gateway.crm.dao.bankaccount;

import com.ips.gateway.crm.dao.bankaccount.entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankAccountDaoImpl implements BankAccountDao{
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> findById(Integer opportunityId) {
        return bankAccountRepository.findById(opportunityId);
    }
}
