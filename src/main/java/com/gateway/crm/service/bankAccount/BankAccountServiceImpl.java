package com.gateway.crm.service.bankAccount;

import com.gateway.crm.dao.bankaccount.BankAccountDao;
import com.gateway.crm.dao.bankaccount.entity.BankAccount;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class BankAccountServiceImpl implements BankAccountService {
    
    @Autowired
    private BankAccountDao bankAccountDao;
    
    @Override
    public List<BankAccount> findById(Integer opportunityId){
        return bankAccountDao.findById(opportunityId);
    }
}