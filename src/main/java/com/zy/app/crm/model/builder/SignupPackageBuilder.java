package com.zy.app.crm.model.builder;

import com.zy.app.rating.campaign.model.CampaignSignupRequest;
import com.zy.app.crm.model.PackageDescriptions;
import com.zy.app.crm.model.SignupPackage;

import java.util.List;

/**
 * User: alexei.bavelski@nordea.com
 * Date: 24-07-2015
 */
public class SignupPackageBuilder {
    int priority;
    String code;
    String name;List<String> fees;
    List<CampaignSignupRequest> campaigns;
    String pricePlanCode;PackageDescriptions descriptions;

    private SignupPackageBuilder() {
    }

    public static SignupPackageBuilder aSignupPackage() {
        return new SignupPackageBuilder();
    }

    public SignupPackageBuilder withPriority(int priority) {
        this.priority = priority;
        return this;
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

    public SignupPackageBuilder withCampaigns(List<CampaignSignupRequest> campaigns) {
        this.campaigns = campaigns;
        return this;
    }

    public SignupPackageBuilder withPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
        return this;
    }

    public SignupPackageBuilder withDescriptions(PackageDescriptions descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public SignupPackageBuilder but() {
        return aSignupPackage().withPriority(priority).withCode(code).withName(name).withFees(fees).withCampaigns(campaigns).withPricePlanCode(pricePlanCode).withDescriptions(descriptions);
    }

    public SignupPackage build() {
        SignupPackage signupPackage = new SignupPackage();
        signupPackage.setPriority(priority);
        signupPackage.setCode(code);
        signupPackage.setName(name);
        signupPackage.setFees(fees);
        signupPackage.setCampaigns(campaigns);
        signupPackage.setPricePlanCode(pricePlanCode);
        signupPackage.setDescriptions(descriptions);
        return signupPackage;
    }
}
