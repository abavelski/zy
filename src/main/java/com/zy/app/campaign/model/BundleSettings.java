package com.zy.app.campaign.model;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public class BundleSettings {

    public enum Period { WEEK, MONTH, YEAR }

    String code;
    String description;
    Integer amount;
    Period periodType;
    Integer periodNumber;
    Integer increment;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Period getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Period periodType) {
        this.periodType = periodType;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }
}
