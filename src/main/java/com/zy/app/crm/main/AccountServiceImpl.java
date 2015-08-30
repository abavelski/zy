package com.zy.app.crm.main;

import com.zy.app.common.main.UtilService;
import com.zy.app.crm.dao.ServiceDao;
import com.zy.app.crm.dao.SubscriptionDao;
import com.zy.app.crm.dao.UserDao;
import com.zy.app.crm.model.Account;
import com.zy.app.crm.model.Service;
import com.zy.app.crm.model.Subscription;
import com.zy.app.crm.model.User;
import com.zy.app.fee.dao.FeeDao;
import com.zy.app.fee.dao.RunningFeeDao;
import com.zy.app.fee.model.Fee;
import com.zy.app.fee.model.RunningFee;
import com.zy.app.rating.campaign.main.SubscriptionCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    @Autowired
    RunningFeeDao runningFeeDao;
    @Autowired
    UtilService utilService;
    @Autowired
    SubscriptionCampaignService subscriptionCampaignService;


    @Override
    public List<Account> findAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        List<Service> services = serviceDao.findAllServices();
        for (Service service : services) {
            Subscription subscription = subscriptionDao.getSubscriptionById(service.getSubscriptionId());
            User user = userDao.getUserById(subscription.getUserId());
            accounts.add(anAccount()
                    .withService(service)
                    .withSubscription(subscription)
                    .withUser(user)
                    .build());
        }
        return sortAccountsByStartDateDescending(accounts);
    }

    private List<Account> sortAccountsByStartDateDescending(List<Account> accounts) {
        Collections.sort(accounts, (o1, o2) -> o2.getSubscription().getStartDate()
                .compareTo(o1.getSubscription().getStartDate()));
        return accounts;

    }

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

    @Override
    @Transactional
    public Account activateAccount(Integer phoneNumber) {
        Service service = serviceDao.findServiceByPhoneNumber(phoneNumber);
        Subscription subscription = subscriptionDao.getSubscriptionById(service.getSubscriptionId());
        User user = userDao.getUserById(subscription.getUserId());
        service.setStatus(Service.Status.ACTIVE);
        subscription.setStatus(Subscription.Status.ACTIVE);
        serviceDao.updateService(service);
        subscriptionDao.updateSubscription(subscription);

        List<RunningFee> runningFees = runningFeeDao.getRunningFeesBySubscriptionId(subscription.getId());
        runningFees.stream().filter(runningFee -> runningFee.getStatus().equals(RunningFee.Status.INITIAL))
                .forEach(runningFee -> {
                    runningFee.setStatus(RunningFee.Status.ACTIVE);
                    runningFee.setNextChargeDate(utilService.getCurrentDate());
                    runningFeeDao.updateRunningFee(runningFee);
                });

        subscriptionCampaignService.activateCampaignsForSubscription(subscription.getId());
        return anAccount()
                .withService(service)
                .withSubscription(subscription)
                .withUser(user)
                .build();
    }
}
