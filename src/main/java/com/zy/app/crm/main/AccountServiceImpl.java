package com.zy.app.crm.main;

import com.zy.app.crm.dao.ServiceDao;
import com.zy.app.crm.dao.SubscriptionDao;
import com.zy.app.crm.dao.UserDao;
import com.zy.app.crm.model.Account;
import com.zy.app.crm.model.Service;
import com.zy.app.crm.model.Subscription;
import com.zy.app.crm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zy.app.crm.model.builder.AccountBuilder.anAccount;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */
@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    ServiceDao serviceDao;

    @Autowired
    SubscriptionDao subscriptionDao;

    @Autowired
    UserDao userDao;

    @Override
    public Account findAccountByPhoneNumber(Integer phoneNumber) {
        Service service = serviceDao.findServiceByPhoneNumber(phoneNumber);
        Subscription subscription = subscriptionDao.getSubscriptionById(service.getSubscriptionId());
        User user = userDao.getUserById(subscription.getUserId());

        return anAccount()
                .withService(service)
                .withSubscription(subscription)
                .withUser(user)
                .build();
    }
}
