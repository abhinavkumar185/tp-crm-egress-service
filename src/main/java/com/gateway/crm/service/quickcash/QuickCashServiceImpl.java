package com.gateway.crm.service.quickcash;

import com.gateway.crm.dao.quickcash.QuickCashApplicationDao;
import com.gateway.crm.dao.quickcash.entity.QuickCashApplicationDetail;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class QuickCashServiceImpl implements QuickCashService{

    @Autowired
    private QuickCashApplicationDao quickCashApplicationDao;

    @Override
    public List<QuickCashApplicationDetail> findByOpportunityId(Integer opportunityId) {

        return quickCashApplicationDao.findByOpportunityId(opportunityId);
    }
}
