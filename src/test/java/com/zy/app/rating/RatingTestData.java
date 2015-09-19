package com.zy.app.rating;

import com.zy.app.rating.prepaid.model.builder.BalanceBuilder;
import com.zy.app.rating.prepaid.model.builder.RatingSessionBuilder;
import com.zy.app.rating.standard.model.buillder.RatingRequestBuilder;

import java.time.LocalDateTime;
import java.time.Month;

import static com.zy.app.rating.prepaid.model.builder.BalanceBuilder.aBalance;
import static com.zy.app.rating.prepaid.model.builder.RatingSessionBuilder.aRatingSession;
import static com.zy.app.rating.standard.model.buillder.RatingRequestBuilder.aRatingRequest;

/**
 * alexei.bavelski@gmail.com
 * 13/09/15
 */
public class RatingTestData {
    public static LocalDateTime CHARGEDATE = LocalDateTime.of(2015, Month.FEBRUARY, 5, 8, 0);

    public static RatingRequestBuilder ratingRequest() {
        return aRatingRequest()
                .withSessionKey("123")
                .withSubscriptionId(1)
                .withPricePlanCode("pp1")
                .withRatingCode("int-voice")
                .withUnits(61)
                .withChargeDate(CHARGEDATE);
    }

    public static BalanceBuilder balance() {
        return aBalance()
                .withId(1)
                .withSubscriptionId(1)
                .withReservedAmount(0)
                .withAmount(100d);
    }

    public static RatingSessionBuilder ratingSession() {
        return aRatingSession()
                .withSessionKey("123")
                .withChargeDate(CHARGEDATE)
                .withPrice(0d)
                .withReservedUnits(61L)
                .withUsedUnits(0L);
    }

}
