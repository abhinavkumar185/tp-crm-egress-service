package com.ips.gateway.crm.dao.kyc.opportunitykyc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ips.gateway.crm.dao.kyc.opportunitykyc.entity.OpportunityKyc;
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
public class OpportunityKycRepository extends JdbcDaoSupport {
	
	@Autowired
	public OpportunityKycRepository(DataSource dataSource){setDataSource(dataSource);}
	
	public List<OpportunityKyc> findByOpportunityId(Integer opportunityId){
		
		String selectClause = "SELECT * FROM opportunity_kyc WHERE opportunity_id = "+opportunityId+" and active_status_id = 1 ";
		return getJdbcTemplate().query(selectClause, new OpportunityKycMapper());
		
	}
	
	private static class OpportunityKycMapper implements RowMapper<OpportunityKyc> {
		
		public OpportunityKyc mapRow(ResultSet rs, int rowNum) throws SQLException {
			ObjectMapper mapper = new ObjectMapper();
			OpportunityKyc opportunityKyc = null;
			try {
				opportunityKyc = OpportunityKyc
						.builder()
						.id(rs.getInt("id"))
						.opportunityId(rs.getInt("opportunity_id"))
						.documentNumber("document_number")
						.cpdtId(rs.getInt("country_product_document_type_id"))
						.kycStatusId(rs.getInt("kyc_status_id"))
						.activeStatusId(rs.getInt("active_status_id"))
						.createdBy(rs.getInt("created_by_id"))
						.channelId(rs.getInt("channel_id"))
						.signzy_response(rs.getString("signzy_response") != null ? mapper.readTree(rs.getString("signzy_response").replaceAll("\\\\","")) : null)
						.merchantResponse(rs.getString("merchant_response") != null ? mapper.readTree(rs.getString("merchant_response").replaceAll("\\\\","")) : null)
						.signzyVerifyResponse(rs.getString("signzy_verify_response") != null ? mapper.readTree(rs.getString("signzy_verify_response").replaceAll("\\\\","")) : null)
						.build();
			}
			catch (Exception ex){
				log.error("OpportunityKyc error : ", ex);
			}
			return opportunityKyc;
		}
	}
}
