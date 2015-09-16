package com.zy.app.rating.standard.main.plugin.rating.voice;

import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;
import com.zy.app.rating.standard.main.plugin.rating.RatingPlugin;

import static com.zy.app.rating.prepaid.model.builder.PrepaidRatingResponseBuilder.aPrepaidRatingResponse;


public class VoiceStandard implements RatingPlugin {


    @Override
    public PrepaidRatingResponse estimate(double maxPrice, long requestedUnits, double rate) {
        if (maxPrice<rate) {
            return aPrepaidRatingResponse()
                    .withStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS)
                    .build();
        }
        long maxMinutes = (long) Math.floor(maxPrice / rate);
        if (requestedUnits<=maxMinutes*60) {
            return aPrepaidRatingResponse()
                    .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                    .withRemainingBalance(maxPrice - calculatePrice(requestedUnits, rate))
                    .withGrantedUnits(requestedUnits)
                    .build();
        } else {
            return aPrepaidRatingResponse()
                    .withStatus(PrepaidRatingStatus.PARTIALLY_GRANTED)
                    .withGrantedUnits(maxMinutes*60)
                    .withRemainingBalance(maxPrice - calculatePrice(maxMinutes*60, rate))
                    .build();
        }
    }

    @Override
    public double calculatePrice(long units, double rate) {
        if (units==0) {
            return 0;
        }
        long rest = units % 60;
        long minutes = units / 60;
        return rate*minutes+( (rest==0) ? 0 : rate);
    }

    @Override
    public String getDescriptionForInvoice() {
        return "";
    }

}
