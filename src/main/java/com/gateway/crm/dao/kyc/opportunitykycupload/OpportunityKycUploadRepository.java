package com.gateway.crm.dao.kyc.opportunitykycupload;

import com.gateway.crm.dao.kyc.opportunitykycupload.entity.OpportunityKycUpload;
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
public class OpportunityKycUploadRepository extends JdbcDaoSupport {
	
	@Autowired
	public OpportunityKycUploadRepository(DataSource dataSource){setDataSource(dataSource);}
	
	public List<OpportunityKycUpload> findByOpportunityId(Integer opportunityId) {
		String selectClause = "SELECT * FROM opportunity_kyc_upload where opportunity_id = "+opportunityId+" AND active_status_id = 1";
		return getJdbcTemplate().query(selectClause,new OpportunityKycUploadMapper());
	}
	
	private static class OpportunityKycUploadMapper implements RowMapper<OpportunityKycUpload> {
	
	@Override
	public OpportunityKycUpload mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		OpportunityKycUpload opportunityKycUpload = OpportunityKycUpload.builder()
				                                            .opportunityId(rs.getInt("opportunity_id"))
				                                            .opportunityKycId(rs.getInt("opportunity_kyc_id"))
				                                            .cpdtId(rs.getInt("country_product_document_type_id"))
				                                            .fileName(rs.getString("file_name"))
				                                            .uploadedById(rs.getInt("uploaded_by_id"))
				                                            .activeStatusId(rs.getInt("active_status_id"))
				                                            .channelId(rs.getInt("channel_id"))
				
				.build();
		return opportunityKycUpload;
	}
	}
}
