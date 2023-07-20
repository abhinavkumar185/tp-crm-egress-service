package com.ips.gateway.crm.dao.contract;

import com.ips.gateway.crm.dao.contract.entity.CostSchedule;
import com.ips.gateway.crm.dao.contract.entity.CostScheduleItem;
import com.ips.gateway.crm.dao.contract.entity.OpportunityProductMapping;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@Repository
public class ContractRepository extends JdbcDaoSupport {

    @Autowired
    public ContractRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List<OpportunityProductMapping> findByOpportunityId(Integer opportunityId) {
        String selectClause = "SELECT id as id," +
                " opportunity_id as opportunityId," +
                " product_id as productId," +
                " bank_id as bankId," +
                " pricing_plan_type_id as pricingPlanTypeId " +
                " FROM opportunity_product_mapping WHERE opportunity_id = "
                + opportunityId + " and status_id= " + 1;
        return getJdbcTemplate().query(selectClause, new ContractRepository.OpportunityProductMappingMapper());
    }

    public CostSchedule findByOpportunityProductMappingId(Integer opportunityProductMappingId) {
        String selectClause = "SELECT id as id," +
                " effective_from_date_time as effectiveFromDateTime," +
                " effective_to_date_time as effectiveToDateTime," +
                " country_id as countryId," +
                " cost_schedule_type_id as costScheduleTypeId," +
                " used_as_template as usedAsTemplate," +
                " created_by_operator_id as createdByOperatorId " +
                " FROM cost_schedule WHERE opportunity_product_mapping_id = "
                +opportunityProductMappingId+ "and active_status_id="+ 1;
        return getJdbcTemplate().query(selectClause, new ContractRepository.CostScheduleMapper()).get(0);
    }

    public List<CostScheduleItem> findByCostScheduleId(Integer costScheduleId) {
        String selectClause = "SELECT created_by_operator_id as createdByOperatorId," +
                " country_id as countryId," +
                " cost_schedule_item_type_id as costScheduleItemTypeId," +
                " created_date_time as createdDateTime," +
                " price as price," +
                " secondary_price as secondaryPrice," +
                " price_percent as pricePercent," +
                " count as count," +
                " telecom_rate_id as telecomRateId," +
                " max_count as maxCount," +
                " unit as unit," +
                " currency_code as currencyCode," +
                " tax_id as taxId," +
                " tax_payable as taxPayable " +
                " FROM cost_schedule_item WHERE cost_schedule_id = ";
        return getJdbcTemplate().query(selectClause, new ContractRepository.CostScheduleItemMapper());
    }

    public static class OpportunityProductMappingMapper implements RowMapper<OpportunityProductMapping> {
        public OpportunityProductMapping mapRow(ResultSet rs, int rowNum) throws SQLException {

            OpportunityProductMapping opportunityProductMapping = OpportunityProductMapping.builder()
                    .id(rs.getInt("id"))
                    .opportunityId(rs.getInt("opportunityId"))
                    .productId(rs.getInt("productId"))
                    .bankId(rs.getInt("bankId"))
                    .pricingPlanTypeId(rs.getInt("pricingPlanTypeId"))
                    .build();
            return opportunityProductMapping;
        }
    }

    public static class CostScheduleMapper implements RowMapper<CostSchedule> {
        public CostSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {

            CostSchedule costSchedule = CostSchedule.builder()
                        .id(rs.getInt("id"))
                        .effectiveFromDateTime(rs.getDate("effectiveFromDateTime"))
                        .effectiveToDateTime(rs.getDate("effectiveToDateTime"))
                        .countryId(rs.getInt("countryId"))
                        .costScheduleTypeId(rs.getInt("costScheduleTypeId"))
                        .usedAsTemplate(rs.getInt("usedAsTemplate"))
                        .createdByOperatorId(rs.getInt("createdByOperatorId"))
                    .build();
            return costSchedule;
        }
    }

    public static class CostScheduleItemMapper implements RowMapper<CostScheduleItem> {
        public CostScheduleItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        CostScheduleItem costScheduleItem = CostScheduleItem.builder()
                    .createdByOperatorId(rs.getInt("createdByOperatorId"))
                    .countryId(rs.getInt("countryId"))
                    .costScheduleItemTypeId(rs.getInt("costScheduleItemTypeId"))
                    .createdDateTime(rs.getDate("createdDateTime"))
                    .price(rs.getBigDecimal("price"))
                    .pricePercent(rs.getBigDecimal("pricePercent"))
                    .secondaryPrice(rs.getBigDecimal("secondaryPrice"))
                    .count(rs.getInt("count"))
                    .telecomRateId(rs.getInt("telecomRateId"))
                    .maxCount(rs.getInt("maxCount"))
                    .unit(rs.getInt("unit"))
                    .currencyCode(rs.getInt("currencyCode"))
                    .taxId(rs.getInt("taxId"))
                    .taxPayable(rs.getBoolean("taxPayable"))
                .build();
        return costScheduleItem;
        }
    }

}
