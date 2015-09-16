package com.zy.app.rating.standard.main.plugin.rating.voice;

import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;
import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class VoiceStandardTest {


    VoiceStandard voiceStandard;

    @Before
    public void setUp() throws Exception {
        voiceStandard = new VoiceStandard();
    }

    @Test
    public void testLessThanMinuteTalk() {
        Double result = voiceStandard.calculatePrice(10, 0.29);
        assertThat(result, closeTo(0.29d, 0.0001));
    }

    @Test
    public void testLongerThanMinuteTalk() {
        Double result = voiceStandard.calculatePrice(61, 0.29);
        assertThat(result, closeTo(0.58d, 0.0001));
    }

    @Test
    public void testZeroSecondTalkIsFree() {
        Double result = voiceStandard.calculatePrice(0, 0.29);
        assertThat(result, closeTo(0, 0.0001));
    }

    @Test
    public void testEstimateInsufficientFunds() {
        PrepaidRatingResponse response = voiceStandard.estimate(0.49, 5, 0.99);
        assertThat("Insufficient funds status",
                PrepaidRatingStatus.INSUFFICIENT_FUNDS, equalTo(response.getStatus()));
    }

    @Test
    public void testEstimateFullyGranted() {
        PrepaidRatingResponse response = voiceStandard.estimate(5, 20, 0.49);
        assertThat("Fully granted status is returned",
                PrepaidRatingStatus.FULLY_GRANTED, equalTo(response.getStatus()));
        assertThat("Granted units number is the same as requested",
                20L, equalTo(response.getGrantedUnits()));
        assertThat("Remaining balance is correct",
                4.51d, closeTo(response.getRemainingBalance(), 0.0001));
    }

    @Test
    public void testEstimatePartiallyGranted() {
        PrepaidRatingResponse response = voiceStandard.estimate(4, 300, 1.99);
        assertThat("Partially granted status is returned",
                PrepaidRatingStatus.PARTIALLY_GRANTED, equalTo(response.getStatus()));
        assertThat("Granted units number is correct",
                120L, equalTo(response.getGrantedUnits()));
        assertThat("Remaining balance is correct",
                0.02d, closeTo(response.getRemainingBalance(), 0.0001));
    }
}