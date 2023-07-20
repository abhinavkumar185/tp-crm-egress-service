package com.ips.gateway.crm.dao.agreement;

import com.ips.gateway.crm.dao.agreement.entity.AgreementPolicy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
@Log4j2
public class AgreementPolicyDaoImpl implements AgreementPolicyDao{

    @Autowired
    private AgreementPolicyRepository agreementPolicyRepository;

    @Override
    public List<AgreementPolicy> findById(Integer opportunityId) {
        return agreementPolicyRepository.findById(opportunityId);
    }
}
