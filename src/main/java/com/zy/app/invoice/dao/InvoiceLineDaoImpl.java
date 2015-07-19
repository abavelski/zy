package com.zy.app.invoice.dao;

import com.zy.app.common.dao.Dao;
import com.zy.app.invoice.model.InvoiceLine;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.zy.app.common.util.DbUtil.fromDB;
import static com.zy.app.common.util.DbUtil.toDB;
import static com.zy.app.invoice.model.buillder.InvoiceLineBuilder.anInvoiceLine;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */
@Component
public class InvoiceLineDaoImpl extends Dao implements InvoiceLineDao {
    @Override
    public int createInvoiceLine(InvoiceLine invoiceLine) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into invoice_line(subscription_id, total, description, invoice_id, charge_date, reference_id)" +
                                    "values( ?, ? ,? ,? ,?,?)", new String[] {"id"});
                    ps.setInt(1, invoiceLine.getSubscriptionId());
                    ps.setDouble(2, invoiceLine.getTotal());
                    ps.setString(3, invoiceLine.getDescription());
                    ps.setInt(4, invoiceLine.getInvoiceId());
                    ps.setTimestamp(5, toDB(invoiceLine.getChargeDate()));
                    ps.setInt(6, invoiceLine.getReferenceId());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    @Override
    public List<InvoiceLine> getInvoiceLinesForInvoice(Integer invoiceId) {
        return jdbcTemplate.query("select * from invoice_line where invoice_id ="+invoiceId,
                new InvoiceLineRowMapper());
    }

    private class InvoiceLineRowMapper implements RowMapper<InvoiceLine> {
        @Override
        public InvoiceLine mapRow(ResultSet rs, int rowNum) throws SQLException {
            return anInvoiceLine()
                    .withId(rs.getInt("id"))
                    .withReferenceId(rs.getInt("reference_id"))
                    .withChargeDate(fromDB(rs.getTimestamp("charge_date")))
                    .withDescription(rs.getString("description"))
                    .withInvoiceId(rs.getInt("invoice_id"))
                    .withSubscriptionId(rs.getInt("subscription_id"))
                    .withTotal(rs.getDouble("total"))
                    .build();
        }
    }

}
