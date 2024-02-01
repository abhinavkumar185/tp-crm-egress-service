package com.gateway.crm.dao.quickcash;

import com.gateway.crm.dao.quickcash.entity.QuickCashApplicationDetail;
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
public class QuickCashApplicationRepository extends JdbcDaoSupport {

    @Autowired
    public QuickCashApplicationRepository(DataSource dataSource){setDataSource(dataSource);}

    public List<QuickCashApplicationDetail> findByOpportunityId(Integer opportunityId) {

        //String selectClause = "SELECT * FROM quick_cash_application qc LEFT JOIN quick_cash_application_status on qcs.quick_cash_application_id = qc.id where opportunity_id = " + opportunityId + " AND active_status_id = 1 ";
        String selectClause = "SELECT qc.loan_stage_id, qc.borrower_first_name, qc.borrower_last_name, qc.father_first_name, qc.father_last_name, qc.mobile,\n" +
                "qc.pan, qc.aadhaar, qc.gender, qc.cibil_consent, qc.residence_type, qc.residence_age, qc.amount, qc.loan_tenure, qc.loan_application_id,\n" +
                "qc.loan_borrower_id, qc.date_of_birth, ca.address_line1 as currentAddressLine1, ca.address_line2 as currentAddressLine2, ca.city as currentCity, \n" +
                "ca.postal_zip as currentPostalZip, ca.state as currentState, ca.state_province_id as currentStateProvienceId, ca.city_id as currentCityId, \n" +
                "ca.country_id as currentCountryId, ca.address_type_id as currentAddressTypeId, pa.address_line1 as permanentAddressLine1, \n" +
                "pa.address_line2 as permanentAddressLine2, pa.address_line3 as permanentAddressLine3,pa.city as permanentCity, pa.postal_zip as permanentPostalZip, pa.state as permanentState, \n" +
                "pa.state_province_id as permanentStateProvienceId, pa.city_id as permanentCityId, \n" +
                "pa.country_id as permanentCountryId, qcs.loan_provider_id, qcs.reason,  pa.address_type_id as permanentAddressTypeId\n" +
                "FROM quick_cash_application qc \n" +
                "LEFT JOIN quick_cash_application_status qcs on qcs.quick_cash_application_id = qc.id \n" +
                "LEFT JOIN address ca on ca.id = qc.current_address_id\n" +
                "LEFT JOIN address pa on pa.id = qc.permanent_address_id\n" +
                "where qc.opportunity_id = " + opportunityId + " AND qc.active_status_id = 1 AND qcs.active_status_id = 1 ;";
        return getJdbcTemplate().query(selectClause, new QuickCashApplicationRepository.QuickCashApplicationDetailMapper());

    }

    private static class QuickCashApplicationDetailMapper implements RowMapper<QuickCashApplicationDetail> {
        public QuickCashApplicationDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            QuickCashApplicationDetail quickCashApplicationDetail = null;
            try {
                quickCashApplicationDetail = QuickCashApplicationDetail.builder()
                        .loanStagesId(rs.getInt("loan_stage_id"))
                        .borrowerFirstName(rs.getString("borrower_first_name"))
                        .borrowerLastName(rs.getString("borrower_last_name"))
                        .fatherFirstName(rs.getString("father_first_name"))
                        .fatherLastName(rs.getString("father_last_name"))
                        .mobileNumber(rs.getString("mobile"))
                        .pan(rs.getString("pan"))
                        .aadhaar(rs.getString("aadhaar"))
                        .gender(rs.getString("gender"))
                        .consent(rs.getString("cibil_consent"))
                        .residenceType(rs.getString("residence_type"))
                        .residenceAge(rs.getString("residence_age") )
                        .amount(rs.getBigDecimal("amount") )
                        .loanApplicationId(rs.getString("loan_application_id"))
                        .loanBorrowerId(rs.getString("loan_borrower_id"))
                        .dateOfBirth(rs.getDate("date_of_birth"))
                        .currentAdressLine1(rs.getString("currentAddressLine1"))
                        .currentAdressLine2(rs.getString("currentAddressLine2"))
                        .currentCity(rs.getString("currentCity"))
                        .currentPostalZip(rs.getString("currentPostalZip"))
                        .currentState(rs.getString("currentState"))
                        .currentStateProvienceId(rs.getInt("currentStateProvienceId"))
                        .currentCityId(rs.getInt("currentCityId"))
                        .currentCountryId(rs.getInt("currentCountryId"))
                        .currentAddressTypeId(rs.getInt("currentAddressTypeId"))
                        .permanentAdressLine1(rs.getString("permanentAddressLine1"))
                        .permanentAdressLine2(rs.getString("permanentAddressLine2"))
                        .permanentAdressLine3(rs.getString("permanentAddressLine3"))
                        .permanentCity(rs.getString("permanentCity"))
                        .permanentCountryId(rs.getInt("permanentCountryId"))
                        .permanentPostalZip(rs.getString("permanentPostalZip"))
                        .permanentState(rs.getString("permanentState"))
                        .permanentStateProvienceId(rs.getInt("permanentStateProvienceId"))
                        .permanentCityId(rs.getInt("permanentCityId"))
                        .permanentAddressTypeId(rs.getInt("permanentAddressTypeId"))
                        .loanProviderId(rs.getInt("loan_provider_id"))
                        .reason(rs.getString("reason"))
                        .loanTenure(rs.getInt("loan_tenure"))
                        .build();

            }
            catch (Exception ex){

                log.error("QuickCashApplicationDetails error : ", ex);
            }
            return quickCashApplicationDetail;
        }
    }
}
