package com.ips.gateway.crm.dao.opportunitysynclog;

import com.ips.gateway.crm.dao.opportunitysynclog.entity.OpportunitySyncLog;
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
public class OpportunitySyncLogRepository extends JdbcDaoSupport {

    @Autowired
    public OpportunitySyncLogRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public OpportunitySyncLog getOpportunitySyncLog(Integer opportunitySyncLogId){
        String selectClause = "SELECT * FROM opportunity_sync_log o WHERE o.id = "+opportunitySyncLogId;
        return getJdbcTemplate().query(selectClause,new OperatorMapper()).get(0);
    }

    public Integer updateById(Integer opportunitySyncLogId){
        String updateClause = "UPDATE opportunity_sync_log " +
                " set batch_entry_status_id = 3 " +
                " WHERE id = "+opportunitySyncLogId;
        return getJdbcTemplate().update(updateClause);
    }

    private static class OperatorMapper implements RowMapper<OpportunitySyncLog> {
        public OpportunitySyncLog mapRow(ResultSet rs, int rowNum) throws SQLException {
            OpportunitySyncLog user = OpportunitySyncLog.builder()
                    .id(rs.getInt("id"))
                    .opportunityId(rs.getInt("opportunity_id"))
                    .invitationId(rs.getInt("invitation_id"))
                    .activeStatusId(rs.getInt("active_status_id"))
                    .batchEntryStatusId(rs.getInt("batch_entry_status_id"))
                    .build();

            return user;
        }
    }
}
