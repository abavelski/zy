package com.zy.app.crm.dao;

import com.zy.app.common.dao.Dao;
import com.zy.app.crm.model.Subscription;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

@Component
public class SubscriptionDaoImpl extends Dao implements SubscriptionDao{

    private static final String INSERT_NEW_SUBSCRIPTION = "insert into subscription(user_id, start_date, price_plan_code, status) " +
            "values (?,?,?,?::subscription_status)";


    @Override
    public int createSubscription(Subscription subscription) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_NEW_SUBSCRIPTION, new String[] {"id"});
                    ps.setInt(1, subscription.getUserId());
                    ps.setTimestamp(2, Timestamp.valueOf(subscription.getStartDate()));
                    ps.setString(3, subscription.getPricePlanCode());
                    ps.setString(4, subscription.getStatus().toString());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }
}
