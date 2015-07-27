package com.zy.app.crm.main;

import com.zy.app.crm.model.AccountSignup;
import com.zy.app.crm.model.SignupPackage;

import java.util.List;

public interface SignupService {

    void createAccount(AccountSignup accountSignup);
    List<SignupPackage> getSortedSignupPackages();

}
