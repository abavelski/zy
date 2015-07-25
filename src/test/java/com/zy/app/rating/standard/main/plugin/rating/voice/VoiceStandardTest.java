package com.zy.app.rating.standard.main.plugin.rating.voice;

import com.zy.app.rating.standard.model.RatingRequest;
import org.junit.Before;
import org.junit.Test;

import static com.zy.app.rating.standard.model.buillder.RatingRequestBuilder.aRatingRequest;
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
        RatingRequest request = aRatingRequest().withAmount(10).build();
        Double result = voiceStandard.calculatePrice(request, 0.29);
        assertThat(result, closeTo(0.29d, 0.0001));
    }

    @Test
    public void testLongerThanMinuteTalk() {
        RatingRequest request = aRatingRequest().withAmount(61).build();
        Double result = voiceStandard.calculatePrice(request, 0.29);
        assertThat(result, closeTo(0.58d, 0.0001));
    }

    @Test
    public void testZeroSecondTalkIsFree() {
        RatingRequest request = aRatingRequest().withAmount(0).build();
        Double result = voiceStandard.calculatePrice(request, 0.29);
        assertThat(result, closeTo(0, 0.0001));
    }
}