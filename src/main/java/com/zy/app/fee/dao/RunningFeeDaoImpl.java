package com.zy.app.fee.dao;

import com.zy.app.common.dao.Dao;
import com.zy.app.fee.model.RunningFee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.zy.app.common.util.DbUtil.fromDB;
import static com.zy.app.common.util.DbUtil.toDB;
import static com.zy.app.fee.model.buillder.RunningFeeBuilder.aRunningFee;

@Component
public class RunningFeeDaoImpl extends Dao implements RunningFeeDao  {

    private static final String INSERT_NEW_FEE = "insert into running_fee(fee_code, subscription_id, next_charge_date, status) " +
            "values (?,?,?,?::running_fee_status)";
    private static final String SELECT_READY_FEES = "select * from running_fee where next_charge_date <=now()";

    @Override
    public int createRunningFee(RunningFee fee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_NEW_FEE, new String[] {"id"});
                    ps.setString(1, fee.getFeeCode());
                    ps.setInt(2, fee.getSubscriptionId());
                    ps.setDate(3, toDB(fee.getNextChargeDate()));
                    ps.setString(4, fee.getStatus().toString());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    @Override
    public List<RunningFee> getReadyToChargeFees() {
        return jdbcTemplate.query(SELECT_READY_FEES, new RunningFeeRowMapper());
    }

    @Override
    public List<RunningFee> getRunningFeesBySubscriptionId(Integer subscriptionId) {
        return jdbcTemplate.query("select * from running_fee where subscription_id = ?",
                new Integer[]{subscriptionId}, new RunningFeeRowMapper());
    }

    @Override
    public void updateRunningFee(RunningFee fee) {
        jdbcTemplate.update("update running_fee set next_charge_date=?, status=cast(? as running_fee_status)" +
                        "where id = ?",
                fee.getNextChargeDate()!=null ? Date.valueOf(fee.getNextChargeDate()):null,
                fee.getStatus().toString(),
                fee.getId());
    }

    private class RunningFeeRowMapper implements RowMapper<RunningFee> {

        @Override
        public RunningFee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return aRunningFee()
                    .withId(rs.getInt("id"))
                    .withFeeCode(rs.getString("fee_code"))
                    .withSubscriptionId(rs.getInt("subscription_id"))
                    .withNextChargeDate(fromDB(rs.getDate("next_charge_date")))
                    .withStatus(RunningFee.Status.valueOf(rs.getString("status")))
                    .build();
        }
    }

}
