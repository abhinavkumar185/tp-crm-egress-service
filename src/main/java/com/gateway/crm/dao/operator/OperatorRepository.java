package com.gateway.crm.dao.operator;

import com.gateway.crm.dao.operator.entity.Operator;
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
public class OperatorRepository extends JdbcDaoSupport {

    @Autowired
    public OperatorRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Operator getOperator(Integer operatorId){
        String selectClause = "SELECT o.id,p.id as partnerId, o.first_name, o.last_name, o.middle_name, " +
                " o.email,o.phone_number, p.uid, o.mobile_number " +
                " FROM operator o join entity_operator_role_mapping map on o.id=map.operator_id " +
                " join partner p on map.partner_id = p.id WHERE o.id = "+operatorId;
        return getJdbcTemplate().query(selectClause,new OperatorMapper()).get(0);
    }


    private static class OperatorMapper implements RowMapper<Operator> {
        public Operator mapRow(ResultSet rs, int rowNum) throws SQLException {
            Operator user = Operator.builder()
                    .id(rs.getInt("id"))
                    .partnerId(rs.getInt("partnerId"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .middleName(rs.getString("middle_name"))
                    .email(rs.getString("email"))
                    .phoneNumber(rs.getString("phone_number"))
                    .uid(rs.getString("uid"))
                    .mobileNumber(rs.getString("mobile_number"))
                    .build();

            return user;
        }
    }
}
