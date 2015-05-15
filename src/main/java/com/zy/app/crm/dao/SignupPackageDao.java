package com.zy.app.crm.dao;


import com.zy.app.crm.model.SignupPackage;

import java.util.List;

public interface SignupPackageDao {

    SignupPackage findPackageByCode(String code);
    List<SignupPackage> getAllSignupPackages();

}
