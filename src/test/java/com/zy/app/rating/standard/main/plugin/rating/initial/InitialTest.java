package com.zy.app.rating.standard.main.plugin.rating.initial;

import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;
import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class InitialTest {

    Initial initial;

    @Before
    public void setUp() throws Exception {
        initial = new Initial();
    }

    @Test
    public void testZeroAmount() {
        Double res = initial.calculatePrice(0, 1.5d);
        assertThat("Result is not null", res, is(not(nullValue())));
        assertThat("Zero amount is priced 0", res, closeTo(0, 0.0001));
    }

    @Test
    public void testNonZeroAmount() {
        Double res = initial.calculatePrice(75, 1.5d);
        assertThat("Zero amount is priced 0", res, closeTo(1.5, 0.0001));
    }

    @Test
    public void testEstimateInsufficientFunds() {
        PrepaidRatingResponse response = initial.estimate(0.49, 5, 0.99);
        assertThat("Insufficient funds status is returned",
                PrepaidRatingStatus.INSUFFICIENT_FUNDS,
                equalTo(response.getStatus()));
    }

    @Test
    public void testEstimateEnoughMoney() {
        PrepaidRatingResponse response = initial.estimate(1.49, 5, 0.99);
        assertThat("Returned status is correct",
                PrepaidRatingStatus.FULLY_GRANTED,
                equalTo(response.getStatus()));

        assertThat("Remaining balance is correct", 0.5d, equalTo(response.getRemainingBalance()));


    }
}