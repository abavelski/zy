package com.zy.app.rating.prepaid.dao.impl;

import com.zy.app.common.dao.Dao;
import com.zy.app.rating.prepaid.dao.RatingSessionDao;
import com.zy.app.rating.prepaid.model.RatingSession;
import org.springframework.stereotype.Component;

import static com.zy.app.common.util.DbUtil.fromDB;
import static com.zy.app.common.util.DbUtil.toDB;
import static com.zy.app.rating.prepaid.model.builder.RatingSessionBuilder.aRatingSession;

/**
 * alexei.bavelski@gmail.com
 * 26/09/15
 */
@Component
public class RatingSessionDaoImpl extends Dao implements RatingSessionDao {

    @Override
    public void createSession(RatingSession rs) {
        jdbcTemplate.update(
                "insert into rating_session(session_key, charge_date, used_units, reserved_units, price)" +
                        "values(?,?,?,?,?)",
                rs.getSessionKey(),
                toDB(rs.getChargeDate()),
                rs.getUsedUnits(),
                rs.getReservedUnits(),
                rs.getPrice());
    }

    @Override
    public RatingSession findSession(String sessionKey) {
        return jdbcTemplate.queryForObject("select * from rating_session where session_key ="+sessionKey,
                (rs, rowNum) -> {
                    return aRatingSession()
                            .withSessionKey(rs.getString("session_key"))
                            .withChargeDate(fromDB(rs.getTimestamp("charge_date")))
                            .withUsedUnits(rs.getLong("used_units"))
                            .withReservedUnits(rs.getLong("reserved_units"))
                            .withPrice(rs.getDouble("price"))
                            .build();
                });
    }

    @Override
    public void updateSession(RatingSession session) {
        jdbcTemplate.update("update rating_session set charge_date = ?, " +
                "used_units=?, " +
                "reserved_units=?, " +
                "price=? " +
                "where session_key=?",
                toDB(session.getChargeDate()),
                session.getUsedUnits(),
                session.getReservedUnits(),
                session.getPrice(),
                session.getSessionKey());
    }

    @Override
    public void deleteSession(String sessionKey) {
            jdbcTemplate.update("delete from rating_session where session_key=?", sessionKey);
    }
}
