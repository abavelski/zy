package com.zy.app.rating.prepaid.main;

import com.zy.app.invoice.main.VatService;
import com.zy.app.rating.prepaid.dao.BalanceDao;
import com.zy.app.rating.prepaid.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * alexei.bavelski@gmail.com
 * 13/10/15
 */
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    BalanceDao balanceDao;
    @Autowired
    VatService vatService;

    @Override
    public double getRemainingBalanceExclVat(int subscriptionId) {
        Balance balance = balanceDao.findBalanceBySubscriptionId(subscriptionId);
        return vatService.calculateExclVat(balance.getAmount());
    }

    @Override
    public void reserveAmountExclVat(int subscriptionId, double reserved, double amount) {
        Balance balance = balanceDao.findBalanceBySubscriptionId(subscriptionId);
        balance.setReservedAmount(
                        balance.getReservedAmount()
                        - (reserved+vatService.calculateVat(reserved))
                        + (amount+vatService.calculateVat(amount))
                    );
        balanceDao.updateBalance(balance);
    }

    @Override
    public void chargeExclVat(int subscriptionId, double reserved, double amount) {
        Balance balance = balanceDao.findBalanceBySubscriptionId(subscriptionId);
        balance.setReservedAmount(
                balance.getReservedAmount()
                        - (reserved+vatService.calculateVat(reserved))
        );
        balance.setAmount(balance.getAmount() - amount+vatService.calculateVat(amount));
        balanceDao.updateBalance(balance);
    }
}
