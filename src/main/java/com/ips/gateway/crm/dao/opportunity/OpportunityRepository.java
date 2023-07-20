package com.ips.gateway.crm.dao.opportunity;

import com.ips.gateway.crm.dao.opportunity.entity.Opportunity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
@Repository
public class OpportunityRepository  extends JdbcDaoSupport {
    @Autowired
    public OpportunityRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Opportunity findById(Integer opportunityId) {
        String selectClause = "SELECT * FROM opportunity op JOIN opportunity_detail opd ON op.id = opd.opportunity_id " +
                " WHERE op.id = "+opportunityId+" AND opd.active_status_id = 1";
        return getJdbcTemplate().query(selectClause,new OpportunityRepository.OpportunityMapper()).get(0);
    }
    
    private static class OpportunityMapper implements RowMapper<Opportunity> {
        public Opportunity mapRow(ResultSet rs, int rowNum) throws SQLException {
            Opportunity opportunity = Opportunity.builder()
                    .businessName(rs.getString("business_name"))
                    .createdBy(rs.getString("created_by"))
                    .lastUpdatedBy(rs.getString("last_updated_by"))
                    .journeyStagesId(rs.getString("journey_stages_id"))
                    .mobileNumber(rs.getString("mobile_number"))
                    .contactName(rs.getString("contact_name"))
                    .opportunityId(rs.getInt("opportunity_id"))
                    .uuid(rs.getString("uid"))
                    .entityTypeId(rs.getInt("entity_type_id"))
                    .roleNestingId(rs.getInt("role_nesting_id"))
                    .webSiteUrl(rs.getString("web_site_url"))
                    .officialWebSiteUrl(rs.getString("official_web_site_url"))
                    .totalSales(rs.getBigDecimal("total_sales"))
                    .cardSales(rs.getBigDecimal("card_sales"))
                    .projectedCardSales(rs.getBigDecimal("projected_card_sales"))
                    .chargeback(rs.getBigDecimal("chargeback_volume"))
                    .refundVolume(rs.getBigDecimal("refund_volume"))
                    .companyRegistrationNumber(rs.getString("company_registration_number"))
                    .years(rs.getInt("years"))
                    .months(rs.getInt("months"))
                    .businessTypeId(rs.getInt("business_type_id"))
                    .build();
            
            return opportunity;
        }
    }
}
