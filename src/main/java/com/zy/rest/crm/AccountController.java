package com.zy.rest.crm;

import com.wordnik.swagger.annotations.Api;
import com.zy.app.crm.main.AccountService;
import com.zy.app.crm.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */

@RestController
@Api(value = "account", description = "Account operations")
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/", method = RequestMethod.GET, produces="application/json")
    public Account getAccountByPhoneNumber(@RequestParam(value="phone") Integer phone) {
        return accountService.findAccountByPhoneNumber(phone);
    }

}
