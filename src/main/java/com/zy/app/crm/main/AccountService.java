package com.zy.app.crm.main;

import com.zy.app.crm.model.Account;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */

public interface AccountService {

    public Account findAccountByPhoneNumber(Integer phoneNumber);

}
