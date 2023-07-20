package com.ips.gateway.crm.dao.agreement;

import com.ips.gateway.crm.dao.agreement.entity.AgreementPolicy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Log4j2
public class AgreementPolicyRepository extends JdbcDaoSupport {
    @Autowired
    public AgreementPolicyRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List<AgreementPolicy> findById(Integer opportunityId) {

        String selectClause = "select opcm.opportunity_id as opportunityId,\n" +
                "opcm.product_id as productId,\n" +
                "opcm.cost_schedule_id as costScheduleId,\n" +
                "opcm.generated_contract_file_name as contractFileName,\n" +
                "opcm.created_by as createdByProductContractMapping,\n" +
                "os.signature_file_name as signatureFileName, \n" +
                "os.created_by as createdBySignature\n" +
                "from opportunity_product_contract_mapping as opcm \n" +
                "LEFT JOIN opportunity_signature os on os.opportunity_id = opcm.opportunity_id \n" +
                "where opcm.opportunity_id="+opportunityId+" and opcm.active_status_id=1 and os.active_status_id=1 ";

        return getJdbcTemplate().query(selectClause,new AgreementPolicyRepository.AgreementPolicyMapper());
    }

    private static class AgreementPolicyMapper implements RowMapper<AgreementPolicy> {
        public AgreementPolicy mapRow(ResultSet rs, int rowNum) throws SQLException {
            AgreementPolicy agreementPolicy= AgreementPolicy.builder()
                    .signatureFileName(rs.getString("signatureFileName"))
                    .createdBySignature(rs.getString("createdBySignature"))
                    .productId(rs.getInt("productId"))
                    .costScheduleId(rs.getInt("costScheduleId"))
                    .contractFileName(rs.getString("contractFileName"))
                    .createdByProductContractMapping(rs.getString("createdByProductContractMapping"))
                    .build();

            return agreementPolicy;
        }
    }


}
