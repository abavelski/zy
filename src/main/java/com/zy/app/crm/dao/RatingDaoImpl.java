package com.zy.app.crm.dao;

import com.zy.app.common.dao.Dao;
import com.zy.app.crm.model.Subscription;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zy.app.crm.model.builder.SubscriptionBuilder.aSubscription;

/**
 * aba
 * 05/04/15
 */
@Component
public class RatingDaoImpl extends Dao implements RatingDao {

    @Override
    public String getPricePlanCodeByPhoneNumber(int phoneNumber) {

        return jdbcTemplate.queryForObject(
                "select price_plan_code from subscription where id in " +
                "(select subscription_id from service where phone_number = ?)",
                new Integer[]{phoneNumber}, String.class);
    }

    @Override
    public Subscription getSubscriptionByPhoneNumber(int phoneNumber) {
        Subscription subscription = jdbcTemplate.queryForObject("select * from subscription where id in " +
                "(select subscription_id from service where phone_number = ?)", (rs, rowNum) -> {
                    return aSubscription()
                            .withId(rs.getInt("id"))
                            .withPricePlanCode(rs.getString("price_plan_code"))
                            .withStatus(Subscription.Status.valueOf(rs.getString("status")))
                            .withStartDate((rs.getTimestamp("start_date").toLocalDateTime()))
                            .withUserId(rs.getInt("user_id"))
                            .build();
                }, phoneNumber);
        return subscription;
    }
}
