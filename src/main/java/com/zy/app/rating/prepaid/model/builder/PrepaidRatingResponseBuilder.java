package com.zy.app.rating.prepaid.model.builder;

import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;

/**
 * alexei.bavelski@gmail.com
 * 12/09/15
 */
public class PrepaidRatingResponseBuilder {
    PrepaidRatingStatus status;
    long grantedUnits;
    double remainingBalance;

    private PrepaidRatingResponseBuilder() {
    }

    public static PrepaidRatingResponseBuilder aPrepaidRatingResponse() {
        return new PrepaidRatingResponseBuilder();
    }

    public PrepaidRatingResponseBuilder withStatus(PrepaidRatingStatus status) {
        this.status = status;
        return this;
    }

    public PrepaidRatingResponseBuilder withGrantedUnits(long grantedUnits) {
        this.grantedUnits = grantedUnits;
        return this;
    }

    public PrepaidRatingResponseBuilder withRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
        return this;
    }

    public PrepaidRatingResponseBuilder but() {
        return aPrepaidRatingResponse().withStatus(status).withGrantedUnits(grantedUnits).withRemainingBalance(remainingBalance);
    }

    public PrepaidRatingResponse build() {
        PrepaidRatingResponse prepaidRatingResponse = new PrepaidRatingResponse();
        prepaidRatingResponse.setStatus(status);
        prepaidRatingResponse.setGrantedUnits(grantedUnits);
        prepaidRatingResponse.setRemainingBalance(remainingBalance);
        return prepaidRatingResponse;
    }
}
