package com.zy.app.crm.model;

import com.zy.app.rating.campaign.model.CampaignSignupRequest;

import java.util.List;

public class SignupPackage {
    int priority;
    String code;
    String name;
    List<String> fees;
    List<CampaignSignupRequest> campaigns;
    String pricePlanCode;
    PackageDescriptions descriptions;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<CampaignSignupRequest> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignSignupRequest> campaigns) {
        this.campaigns = campaigns;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFees() {
        return fees;
    }

    public void setFees(List<String> fees) {
        this.fees = fees;
    }

    public String getPricePlanCode() {
        return pricePlanCode;
    }

    public void setPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
    }

    public PackageDescriptions getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(PackageDescriptions descriptions) {
        this.descriptions = descriptions;
    }
}
