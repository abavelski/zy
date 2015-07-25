package com.zy.app.rating.standard.main.plugin.rating.initial;

import com.zy.app.rating.standard.model.RatingRequest;
import org.junit.Before;
import org.junit.Test;

import static com.zy.app.rating.standard.model.buillder.RatingRequestBuilder.aRatingRequest;
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
        RatingRequest request = aRatingRequest()
                .withAmount(0)
                .build();
        Double res = initial.calculatePrice(request, 1.5d);
        assertThat("Result is not null", res, is(not(nullValue())));
        assertThat("Zero amount is priced 0", res, closeTo(0, 0.0001));
    }

    @Test
    public void testNonZeroAmount() {
        RatingRequest request = aRatingRequest()
                .withAmount(75)
                .build();
        Double res = initial.calculatePrice(request, 1.5d);
        assertThat("Zero amount is priced 0", res, closeTo(1.5, 0.0001));
    }
}