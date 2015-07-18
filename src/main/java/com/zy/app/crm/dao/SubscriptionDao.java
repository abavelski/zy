package com.zy.app.crm.dao;


import com.zy.app.crm.model.Subscription;

public interface SubscriptionDao {

    public Integer createSubscription(Subscription subscription);
    public Subscription getSubscriptionById(Integer id);

}
