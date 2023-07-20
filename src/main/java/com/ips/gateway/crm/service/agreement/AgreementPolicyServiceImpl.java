package com.ips.gateway.crm.service.agreement;

import com.ips.gateway.crm.dao.agreement.AgreementPolicyDao;
import com.ips.gateway.crm.dao.agreement.entity.AgreementPolicy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AgreementPolicyServiceImpl implements AgreementPolicyService{
    @Autowired
    private AgreementPolicyDao agreementPolicyDao;

    @Override
    public List<AgreementPolicy> findById(Integer opportunityId){
        return agreementPolicyDao.findById(opportunityId);
    }

}
