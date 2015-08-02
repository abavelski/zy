package com.zy.app.crm.main;

import com.zy.app.crm.model.Account;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */

public interface AccountService {

    List<Account> findAllAccounts();
    Account findAccountByPhoneNumber(Integer phoneNumber);


}
