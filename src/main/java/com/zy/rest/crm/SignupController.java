package com.zy.rest.crm;

import com.wordnik.swagger.annotations.Api;
import com.zy.app.crm.dao.SignupPackageDao;
import com.zy.app.crm.main.SignupService;
import com.zy.app.crm.model.AccountSignup;
import com.zy.app.crm.model.SignupPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(value = "signup", description = "Signup operations")
@RequestMapping("/api/signup")
public class SignupController {

    @Autowired
    SignupService signupService;
    @Autowired
    SignupPackageDao signupPackageDao;

    @RequestMapping(method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<Void> createAccount(@RequestBody AccountSignup accountSignup) {
        signupService.createAccount(accountSignup);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value= "/packages", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<SignupPackage>> getAllSignupPackages() {
        return new ResponseEntity<>(signupPackageDao.getAllSignupPackages(), HttpStatus.OK);
    }

    @RequestMapping(value= "/packages/{code}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<SignupPackage> getSignupPackage(@PathVariable String code) {
        return new ResponseEntity<>(signupPackageDao.findPackageByCode(code), HttpStatus.OK);
    }

}
