package com.zy.rest.crm;

import com.zy.app.crm.main.AccountService;
import com.zy.app.crm.model.Account;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    public List<Account> getAccounts() {
        return accountService.findAllAccounts();

    }

    @RequestMapping(value="/{phone}", method = RequestMethod.GET, produces="application/json")
    public Account getAccountByPhoneNumber(@PathVariable Integer phone) {
        return accountService.findAccountByPhoneNumber(phone);
    }

    @RequestMapping(value="/{phone}/activate", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<Account> activateAccount(@PathVariable Integer phone) {
        Account acc = accountService.activateAccount(phone);
        return new ResponseEntity<>(acc, HttpStatus.OK);
    }

}
