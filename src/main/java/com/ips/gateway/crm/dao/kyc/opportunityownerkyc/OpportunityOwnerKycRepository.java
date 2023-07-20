package com.ips.gateway.crm.dao.kyc.opportunityownerkyc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ips.gateway.crm.dao.kyc.opportunityownerkyc.entity.OpportunityOwnerKyc;
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
public class OpportunityOwnerKycRepository extends JdbcDaoSupport {
	
	@Autowired
	public OpportunityOwnerKycRepository(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public List<OpportunityOwnerKyc> findByOpportunityId(Integer opportunityId) {
		
		String selectClause = "SELECT * FROM opportunity_owner_kyc where opportunity_id = " + opportunityId + " AND active_status_id = 1";
		return getJdbcTemplate().query(selectClause, new OpportunityOwnerKycMapper());
		
	}
	
	private static class OpportunityOwnerKycMapper implements RowMapper<OpportunityOwnerKyc> {
		
		@Override
		public OpportunityOwnerKyc mapRow(ResultSet rs, int rowNum) throws SQLException {
			ObjectMapper mapper = new ObjectMapper();
			OpportunityOwnerKyc opportunityOwnerKyc = null;
			try {
				opportunityOwnerKyc = OpportunityOwnerKyc.builder()
						.opportunityId(rs.getInt("opportunity_id"))
						.documentNumber(rs.getString("document_number"))
						.documentTypeId(rs.getInt("document_type_id"))
						.fileName(rs.getString("file_name"))
						.psdsId(rs.getInt("process_signed_document_status_id"))
						.uploadedBy(rs.getInt("uploaded_by_id"))
						.cpdtId(rs.getInt("country_product_document_type_id"))
						.kycStatusId(rs.getInt("kyc_status_id"))
						.activeStatusId(rs.getInt("active_status_id"))
						.createdById(rs.getInt("created_by_id"))
						.channelId(rs.getInt("channel_id"))
						.signzy_response(rs.getString("signzy_response") != null ? mapper.readTree(rs.getString("signzy_response").replaceAll("\\\\", "")) : null)
						.merchantResponse(rs.getString("merchant_response") != null ? mapper.readTree(rs.getString("merchant_response").replaceAll("\\\\", "")) : null)
						.signzyVerifyResponse(rs.getString("signzy_verify_response") != null ? mapper.readTree(rs.getString("signzy_verify_response").replaceAll("\\\\", "")) : null)
						.build();
			}
			catch (Exception ex){
				
				log.error("OpportunityOwnerKyc error : ", ex);
			}
			return opportunityOwnerKyc;
		}
	}
}
