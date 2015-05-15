package com.zy.app.cdr.dao;

import com.zy.app.cdr.model.BillingRecord;

/**
 * aba
 * 23/03/15
 */
public interface BillingRecordDao {

    int insertBillingRecord(BillingRecord br);
    void updateBillingRecord(BillingRecord br);

}
