package com.zy.app.invoice.dao;

import com.zy.app.common.dao.Dao;
import com.zy.app.invoice.model.InvoiceLine;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;

import static com.zy.app.common.util.DbUtil.toDB;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */
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
}
