package com.zy.app.crm.model;

import java.util.List;

public class SignupPackage {
    String code;
    String name;
    List<String> fees;
    String pricePlanCode;
    PackageDescriptions descriptions;

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
