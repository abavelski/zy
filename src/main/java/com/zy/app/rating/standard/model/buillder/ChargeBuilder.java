package com.zy.app.rating.standard.model.buillder;

import com.zy.app.rating.standard.main.plugin.rating.RatingType;
import com.zy.app.rating.standard.model.Charge;

/**
 * aba
 * 26/02/15
 */
public class ChargeBuilder {
    private Double rate;
    private String code;
    private RatingType ratingPlugin;

    private ChargeBuilder() {
    }

    public static ChargeBuilder aCharge() {
        return new ChargeBuilder();
    }

    public ChargeBuilder withRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public ChargeBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public ChargeBuilder withRatingPlugin(RatingType ratingPlugin) {
        this.ratingPlugin = ratingPlugin;
        return this;
    }

    public ChargeBuilder but() {
        return aCharge().withRate(rate).withCode(code).withRatingPlugin(ratingPlugin);
    }

    public Charge build() {
        Charge charge = new Charge();
        charge.setRate(rate);
        charge.setCode(code);
        charge.setRatingPlugin(ratingPlugin);
        return charge;
    }
}
