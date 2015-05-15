package com.zy.app.crm.model.builder;

import com.zy.app.crm.model.Service;

/**
 * aba
 * 22/03/15
 */
public class ServiceBuilder {
    int id;
    int subscriptionId;
    Integer phoneNumber;Service.Status status;

    private ServiceBuilder() {
    }

    public static ServiceBuilder aService() {
        return new ServiceBuilder();
    }

    public ServiceBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ServiceBuilder withSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public ServiceBuilder withPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ServiceBuilder withStatus(Service.Status status) {
        this.status = status;
        return this;
    }

    public ServiceBuilder but() {
        return aService().withId(id).withSubscriptionId(subscriptionId).withPhoneNumber(phoneNumber).withStatus(status);
    }

    public Service build() {
        Service service = new Service();
        service.setId(id);
        service.setSubscriptionId(subscriptionId);
        service.setPhoneNumber(phoneNumber);
        service.setStatus(status);
        return service;
    }
}
