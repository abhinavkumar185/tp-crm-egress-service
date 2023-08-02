package com.gateway.crm.dao.bankaccount;

import com.gateway.crm.dao.bankaccount.entity.BankAccount;
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
public class BankAccountRepository extends JdbcDaoSupport {
    @Autowired
    public BankAccountRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List<BankAccount> findById(Integer opportunityId) {
        String selectClause = "select oa.opportunity_id as  opportunityId, oa.created_by as createdBy, oa.product_id as productId,\n" +
                "oa.bank_account_id as bankAccountId, ba.bank_id as bankId, ba.account_no as accountNumber,\n" +
                "ba.ifsc_code as ifsc, \n" +
                "ba.account_holder_name as accountHolderName\n" +
                "from opportunity_account oa \n" +
                "LEFT JOIN bank_account as ba on ba.id = oa.bank_account_id\n" +
                "where oa.opportunity_id=" + opportunityId + " and oa.active_status_id=1";

        return getJdbcTemplate().query(selectClause, new BankAccountRepository.BankAccountMapper());
    }


    private static class BankAccountMapper implements RowMapper<BankAccount> {
        public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            BankAccount bankAccount = null;
            try {
                bankAccount = BankAccount.builder()
                        .createdBy(rs.getString("createdBy"))
                        .productId(rs.getInt("productId"))
                        .bankAccountId(rs.getInt("bankAccountId"))
                        .bankId(rs.getInt("bankId"))
                        .accountNumber(rs.getString("accountNumber"))
                        .accountHolderName(rs.getString("accountHolderName"))
                        .ifsc(rs.getString("ifsc"))
                        .opportunityId(rs.getInt("opportunityId"))
                        .build();
            }
            catch (Exception ex){
                log.error("OpportunityKyc error : ", ex);
            }
            return bankAccount;
        }

    }
}
