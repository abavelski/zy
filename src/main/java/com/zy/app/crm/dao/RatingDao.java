package com.zy.app.crm.dao;

import com.zy.app.crm.model.Subscription;

/**
 * aba
 * 23/03/15
 */
public interface RatingDao {
    
    public String getPricePlanCodeByPhoneNumber(int phoneNumber);
    public Subscription getSubscriptionByPhoneNumber(int phoneNumber);
}
