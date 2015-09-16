package com.zy.app.rating.prepaid.dao;

import com.zy.app.rating.prepaid.model.Balance;

/**
 * alexei.bavelski@gmail.com
 * 12/09/15
 */
public interface BalanceDao {

    Balance findBalanceBySubscriptionId(int subscriptionId);
    void updateBalance(Balance balance);

}
