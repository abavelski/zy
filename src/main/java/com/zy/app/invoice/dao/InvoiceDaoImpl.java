package com.zy.app.invoice.dao;

import com.zy.app.common.dao.Dao;
import com.zy.app.invoice.model.Invoice;
import com.zy.app.invoice.model.InvoiceLine;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

import static com.zy.app.common.util.DbUtil.fromDB;
import static com.zy.app.common.util.DbUtil.toDB;
import static com.zy.app.invoice.model.buillder.InvoiceBuilder.anInvoice;

@Component
public class InvoiceDaoImpl extends Dao implements InvoiceDao {

    @Override
    public int createInvoice(Invoice invoice) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into invoice(subscription_id, start_date, end_date, status, total_excl_vat, total_vat) " +
                                "values(?,?,?,cast(? as invoice_status),?,?)" , new String[] {"id"});
                    ps.setInt(1, invoice.getSubscriptionId());
                    ps.setDate(2, toDB(invoice.getStartDate()));
                    ps.setDate(3, toDB(invoice.getEndDate()));
                    ps.setString(4, invoice.getStatus().toString());
                    ps.setDouble(5, invoice.getTotalExclVat());
                    ps.setDouble(6, invoice.getTotalVat());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(
                "update invoice set start_date = ?, end_date = ?, status = cast(? as invoice_status), " +
                        "total_excl_vat = ?, total_vat = ?, close_date = ? where id = ?",
                toDB(invoice.getStartDate()),
                toDB(invoice.getEndDate()),
                invoice.getStatus().toString(),
                invoice.getTotalExclVat(),
                invoice.getTotalVat(),
                toDB(invoice.getCloseDate()),
                invoice.getId());
    }


    @Override
    public List<Invoice> getInvoicesBySubscriptionIdAndStatus(int subscriptionId, Invoice.Status status) {
        List<Invoice> invoices = this.jdbcTemplate.query(
                "select * from invoice where subscription_id = "+subscriptionId+" and status='"
                        + status+"' order by id",
                (rs, rowNum) -> {
                    return anInvoice()
                            .withId(rs.getInt("id"))
                            .withStartDate(fromDB(rs.getDate("start_date")))
                            .withEndDate(fromDB(rs.getDate("end_date")))
                            .withCloseDate(fromDB(rs.getTimestamp("close_date")))
                            .withStatus(Invoice.Status.valueOf(rs.getString("status")))
                            .withTotalExclVat(rs.getDouble("total_excl_vat"))
                            .withTotalVat(rs.getDouble("total_vat"))
                            .build();
                });
        return invoices;
    }




}
