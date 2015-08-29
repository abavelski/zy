package com.zy.app.crm.dao;

import com.zy.app.crm.model.Service;

import java.util.List;

/**
 * aba
 * 21/03/15
 */
public interface ServiceDao {

    int createService(Service service);
    void updateService(Service service);
    Service findServiceByPhoneNumber(Integer phoneNumber);
    List<Service> findAllServices();

}
