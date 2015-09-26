package com.zy.app.rating.prepaid.dao.impl;

import com.zy.app.common.dao.Dao;
import com.zy.app.rating.prepaid.dao.BalanceDao;
import com.zy.app.rating.prepaid.model.Balance;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

import static com.zy.app.rating.prepaid.model.builder.BalanceBuilder.aBalance;

/**
 * alexei.bavelski@gmail.com
 * 26/09/15
 */
@Component
public class BalanceDaoImpl extends Dao implements BalanceDao {

    @Override
    public int createBalance(Balance balance) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into balance(subscription_id, amount, reserved_amount) " +
                                    "values(?,?,?)" , new String[] {"id"});
                    ps.setInt(1, balance.getSubscriptionId());
                    ps.setDouble(2, balance.getAmount());
                    ps.setDouble(3, balance.getReservedAmount());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    @Override
    public Balance findBalanceBySubscriptionId(int subscriptionId) {
        return jdbcTemplate.queryForObject("select * from balance where subscription_id = " + subscriptionId,
                (rs, rowNum) -> {
                    return aBalance()
                            .withId(rs.getInt("id"))
                            .withSubscriptionId(rs.getInt("subscription_id"))
                            .withAmount(rs.getDouble("amount"))
                            .withReservedAmount(rs.getDouble("reserved_amount"))
                            .build();
                });
    }

    @Override
    public void updateBalance(Balance balance) {
        jdbcTemplate.update("update balance set amount = ?, reserved_amount=? where id = ?",
                balance.getAmount(),
                balance.getReservedAmount(),
                balance.getId()
                );
    }
}
