package com.gateway.crm.dao.quickcash;

import com.gateway.crm.dao.quickcash.entity.QuickCashApplicationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuickCashApplicationDaoImpl implements  QuickCashApplicationDao{

    @Autowired
    private QuickCashApplicationRepository quickCashApplicationRepository;

    @Override
    public List<QuickCashApplicationDetail> findByOpportunityId(Integer opportunityId) {
        return quickCashApplicationRepository.findByOpportunityId(opportunityId);
    }
}
