package com.zy.app.crm.dao;

import com.zy.app.common.dao.Dao;
import com.zy.app.crm.model.Service;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

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
}
