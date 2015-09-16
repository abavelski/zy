package com.zy.app.rating.standard.main.plugin.rating.initial;

import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;
import com.zy.app.rating.standard.main.plugin.rating.RatingPlugin;

import static com.zy.app.rating.prepaid.model.builder.PrepaidRatingResponseBuilder.aPrepaidRatingResponse;

public class Initial implements RatingPlugin {

    @Override
    public PrepaidRatingResponse estimate(double maxPrice, long requestedUnits, double rate) {
        if (maxPrice<rate) {
            return aPrepaidRatingResponse()
                    .withStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS)
                    .build();
        } else {
            return aPrepaidRatingResponse()
                    .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                    .withRemainingBalance(maxPrice - rate)
                    .build();
        }
    }

    @Override
    public double calculatePrice(long units, double rate) {
        return (units>0) ? rate : 0;
    }

    @Override
    public String getDescriptionForInvoice() {
        return "Initial";
    }

}
