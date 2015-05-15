package com.zy.app.cdr.dao;

import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.common.dao.Dao;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

import static com.zy.app.common.util.DbUtil.toDB;

/**
 * aba
 * 23/03/15
 */
@Component
public class BillingRecordDaoImpl extends Dao implements BillingRecordDao {

    @Override
    public int insertBillingRecord(BillingRecord br) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into billing_record(phone_number, destination, amount, charge_date, usage_type, traffic_type, status)" +
                                    "values( ?, ? ,? ,? ,?::usage_type,?::traffic_type, ?::billing_record_status)", new String[] {"id"});
                    ps.setInt(1, br.getPhoneNumber());
                    ps.setString(2, br.getDestination());
                    ps.setLong(3, br.getAmount());
                    ps.setTimestamp(4, toDB(br.getChargeDate()));
                    ps.setString(5, br.getUsageType().toString());
                    ps.setString(6, br.getTrafficType().toString());
                    ps.setString(7, br.getStatus().toString());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateBillingRecord(BillingRecord br) {

    }
}
