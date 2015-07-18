package com.zy.app.crm.dao;

import com.zy.app.crm.model.Service;

/**
 * aba
 * 21/03/15
 */
public interface ServiceDao {

    public int createService(Service service);
    public Service findServiceByPhoneNumber(Integer phoneNumber);

}
