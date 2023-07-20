package com.ips.gateway.crm.dao.invitation;

import com.ips.gateway.crm.dao.invitation.entity.Invitation;
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
public class InvitationRepository  extends JdbcDaoSupport {
    @Autowired
    public InvitationRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }


    public Invitation findById(Integer invitationId, Integer syncId) {
        String selectClause = "SELECT " + syncId + " as syncId , i.id, i.name, i.first_name, i.last_name, i.email, i.mobile_number," +
                " i.address_line1, i.address_line2, " +
                " i.postal_zip, i.state_province_id, i.city,i.isd_code, i.country_id, opl.passcode, " +
                " eorm.role_nesting_id, o.uid, p.uid as clientId" +
                "  FROM invitation i left join " +
                " entity_operator_role_mapping eorm on i.partner_id = eorm.partner_id and eorm.active_status_id=1 " +
                " left join operator o on eorm.operator_id = o.id " +
                " left join partner p on eorm.partner_id = p.id " +
                " left join operator_passcode_log opl on o.id = opl.operator_id and opl.active_status_id = 1" +
                " WHERE i.id = "+invitationId;
        return getJdbcTemplate().query(selectClause,new InvitationMapper()).get(0);
    }
    private static class InvitationMapper implements RowMapper<Invitation> {
        public Invitation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Invitation invitation = Invitation.builder()
                    .syncId(rs.getInt("syncId"))
                    .id(rs.getInt("id"))
                    .businessName(rs.getString("name"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .email(rs.getString("email"))
                    .phoneNumber(rs.getString("mobile_number"))
                    .mobileNumber(rs.getString("mobile_number"))
                    .addressLine1(rs.getString("address_line1"))
                    .addressLine2(rs.getString("address_line2"))
                    .postalZip(rs.getString("postal_zip"))
                    .city(rs.getString("city"))
                    .stateProvince(rs.getInt("state_province_id"))
                    .isdCode(rs.getString("isd_code"))
                    .countryId(rs.getInt("country_id"))
                    .passcode(rs.getString("passcode"))
                    .roleNestingId(rs.getString("role_nesting_id") != null ? rs.getInt("role_nesting_id") : null)
                    .operatorUid(rs.getString("uid") != null ? rs.getString("uid") : null)
                    .clientId(rs.getString("clientId") != null ? rs.getString("clientId") : null)
                    .build();

            return invitation;
        }
    }
}
