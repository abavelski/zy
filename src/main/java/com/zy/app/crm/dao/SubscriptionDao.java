package com.zy.app.crm.dao;


import com.zy.app.crm.model.Subscription;

public interface SubscriptionDao {

    Integer createSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    Subscription getSubscriptionById(Integer id);

}
