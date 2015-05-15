package com.zy.app.anumber.dao;

import com.zy.app.anumber.model.ANumber;
import com.zy.app.common.dao.Dao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.zy.app.anumber.model.builder.ANumberBuilder.anANumber;
import static com.zy.app.common.util.DbUtil.fromDB;
import static com.zy.app.common.util.DbUtil.toDB;


@Component
public class ANumberDaoImpl extends Dao implements ANumberDao {

    @Override
    public void createANumber(ANumber aNumber) {
        jdbcTemplate.update(
                "insert into a_number(number, status, type, created)" +
                        "values(?,cast(? as anumber_status),cast(? as anumber_type),?)",
                aNumber.getNumber(),
                aNumber.getStatus().toString(),
                aNumber.getType().toString(),
                toDB(aNumber.getCreated())
        );
    }

    @Override
    public void updateANumber(ANumber aNumber) {
        jdbcTemplate.update("update a_number set status= cast(? as anumber_status), " +
                "type=cast(? as anumber_type), " +
                "reserved_until=?, " +
                "assigned_to_service_id=?, " +
                "reservation_id=? " +
                "where number = ?",
                aNumber.getStatus().toString(),
                aNumber.getType().toString(),
                toDB(aNumber.getReservedUntil()),
                aNumber.getAssignedToServiceId(),
                aNumber.getReservationId(),
                aNumber.getNumber());
    }

    @Override
    public ANumber getANumber(Integer number) {
        return jdbcTemplate.queryForObject("select * from a_number where number =?",
                new Integer[]{number},
                new ANumberRowMapper());
    }

    @Override
    public ANumber getReservedANumber(String reservationId) {
        return jdbcTemplate.queryForObject("select * from a_number where reservation_id =?",
                new String[]{reservationId},
                new ANumberRowMapper());
    }

    public List<Integer> getOpenNumbers(Integer nr, ANumber.Type type) {
        return jdbcTemplate.query("select number from a_number where status=cast('"+ANumber.Status.OPEN
                        + "' as anumber_status) and type= cast(? as anumber_type) "
                        + "and (reserved_until is null or reserved_until < ?) limit ?",
                new Object[]{type.toString(), Timestamp.valueOf(LocalDateTime.now()), nr},
                (rs, rowNum) -> rs.getInt("number"));
    }

    private class ANumberRowMapper implements RowMapper<ANumber> {
        @Override
        public ANumber mapRow(ResultSet rs, int rowNum) throws SQLException {
            return anANumber()
                        .withNumber(rs.getInt("number"))
                        .withStatus(ANumber.Status.valueOf(rs.getString("status")))
                        .withType(ANumber.Type.valueOf(rs.getString("type")))
                        .withReservedUntil(fromDB(rs.getTimestamp("reserved_until")))
                        .withAssignedToServiceId(rs.getInt("assigned_to_service_id"))
                        .withCreated(fromDB(rs.getTimestamp("created")))
                        .withReservationId(rs.getString("reservation_id"))
                    .build();
        }
    }
}
