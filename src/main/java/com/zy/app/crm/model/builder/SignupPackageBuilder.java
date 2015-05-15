package com.zy.app.crm.model.builder;

import com.zy.app.crm.model.SignupPackage;

import java.util.List;

/**
 * aba
 * 26/02/15
 */
public class SignupPackageBuilder {
    String code;
    String name;List<String> fees;
    String pricePlanCode;

    private SignupPackageBuilder() {
    }

    public static SignupPackageBuilder aSignupPackage() {
        return new SignupPackageBuilder();
    }

    public SignupPackageBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public SignupPackageBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SignupPackageBuilder withFees(List<String> fees) {
        this.fees = fees;
        return this;
    }

    public SignupPackageBuilder withPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
        return this;
    }

    public SignupPackageBuilder but() {
        return aSignupPackage().withCode(code).withName(name).withFees(fees).withPricePlanCode(pricePlanCode);
    }

    public SignupPackage build() {
        SignupPackage signupPackage = new SignupPackage();
        signupPackage.setCode(code);
        signupPackage.setName(name);
        signupPackage.setFees(fees);
        signupPackage.setPricePlanCode(pricePlanCode);
        return signupPackage;
    }
}
