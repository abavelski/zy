package com.zy.app.crm.model;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */
public class Account {
    private Subscription subscription;
    private Service service;
    private User user;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
