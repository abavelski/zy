package com.zy.app.rating.prepaid.main;

/**
 * alexei.bavelski@gmail.com
 * 13/10/15
 */
public interface BalanceService {

    double getRemainingBalanceExclVat(int subscriptionId);
    void reserveAmountExclVat(int subscriptionId, double reserved, double amount);
    void chargeExclVat(int subscriptionId, double reserved, double amount);
    void creditBalance(int subscriptionId, double amount);

}
