package com.zy.app.rating;

import com.zy.app.rating.standard.model.buillder.RatingRequestBuilder;

import java.time.LocalDateTime;
import java.time.Month;

import static com.zy.app.rating.standard.model.buillder.RatingRequestBuilder.aRatingRequest;

/**
 * alexei.bavelski@gmail.com
 * 13/09/15
 */
public class RatingTestData {
    public static LocalDateTime CHARGEDATE = LocalDateTime.of(2015, Month.FEBRUARY, 5, 8, 0);

    public static RatingRequestBuilder ratingRequest() {
        return aRatingRequest()
                .withSubscriptionId(1)
                .withPricePlanCode("pp1")
                .withRatingCode("int-voice")
                .withUnits(61)
                .withChargeDate(CHARGEDATE);
    }


}
