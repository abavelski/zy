package com.zy.app.crm.dao;

import com.zy.app.common.dao.Dao;
import com.zy.app.crm.model.Service;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.zy.app.crm.model.builder.ServiceBuilder.aService;


/**
 * aba
 * 21/03/15
 */
@Component
public class ServiceDaoImpl extends Dao implements ServiceDao {
    @Override
    public int createService(Service service) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into service(subscription_id, phone_number, status) values (?,?,?::service_status)",
                            new String[] {"id"});
                    ps.setInt(1, service.getSubscriptionId());
                    ps.setInt(2, service.getPhoneNumber());
                    ps.setString(3, service.getStatus().toString());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    @Override
    public Service findServiceByPhoneNumber(Integer phoneNumber) {
        return jdbcTemplate.queryForObject("select * from service where phone_number = ?",
                new Integer[]{phoneNumber},
                new ServiceRowMapper());
    }

    @Override
    public List<Service> findAllServices() {
        return jdbcTemplate.query("select * from service", new ServiceRowMapper());
    }

    private class ServiceRowMapper implements RowMapper<Service> {
        @Override
        public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
            return aService()
                    .withId(rs.getInt("id"))
                    .withPhoneNumber(rs.getInt("phone_number"))
                    .withSubscriptionId(rs.getInt("subscription_id"))
                    .withStatus(Service.Status.valueOf(rs.getString("status")))
                    .build();
        }
    }


}
