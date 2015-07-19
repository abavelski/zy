package com.zy.app.crm.model.builder;

import com.zy.app.crm.model.Account;
import com.zy.app.crm.model.Service;
import com.zy.app.crm.model.Subscription;
import com.zy.app.crm.model.User;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */
public class AccountBuilder {
    private Subscription subscription;
    private Service service;
    private User user;

    private AccountBuilder() {
    }

    public static AccountBuilder anAccount() {
        return new AccountBuilder();
    }

    public AccountBuilder withSubscription(Subscription subscription) {
        this.subscription = subscription;
        return this;
    }

    public AccountBuilder withService(Service service) {
        this.service = service;
        return this;
    }

    public AccountBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public AccountBuilder but() {
        return anAccount().withSubscription(subscription).withService(service).withUser(user);
    }

    public Account build() {
        Account account = new Account();
        account.setSubscription(subscription);
        account.setService(service);
        account.setUser(user);
        return account;
    }
}
