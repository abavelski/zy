package com.zy.app.rating.campaign.model.builder;

import com.zy.app.rating.campaign.model.BundleSettings;

/**
 * User: alexei.bavelski@gmail.com
 * Date: 22-07-2015
 */
public class BundleSettingsBuilder {
    String code;
    String description;
    Integer amount;
    BundleSettings.Period periodType;
    Integer periodNumber;
    Integer increment;

    private BundleSettingsBuilder() {
    }

    public static BundleSettingsBuilder aBundleSettings() {
        return new BundleSettingsBuilder();
    }

    public BundleSettingsBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public BundleSettingsBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public BundleSettingsBuilder withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public BundleSettingsBuilder withPeriodType(BundleSettings.Period periodType) {
        this.periodType = periodType;
        return this;
    }

    public BundleSettingsBuilder withPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
        return this;
    }

    public BundleSettingsBuilder withIncrement(Integer increment) {
        this.increment = increment;
        return this;
    }

    public BundleSettingsBuilder but() {
        return aBundleSettings().withCode(code).withDescription(description).withAmount(amount).withPeriodType(periodType).withPeriodNumber(periodNumber).withIncrement(increment);
    }

    public BundleSettings build() {
        BundleSettings bundleSettings = new BundleSettings();
        bundleSettings.setCode(code);
        bundleSettings.setDescription(description);
        bundleSettings.setAmount(amount);
        bundleSettings.setPeriodType(periodType);
        bundleSettings.setPeriodNumber(periodNumber);
        bundleSettings.setIncrement(increment);
        return bundleSettings;
    }
}
