package com.zy.app.rating.standard.main;

import com.zy.app.rating.standard.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.standard.main.plugin.rating.RatingType;
import com.zy.app.rating.standard.main.plugin.rating.initial.Initial;
import com.zy.app.rating.standard.main.plugin.rating.voice.VoiceStandard;
import com.zy.app.rating.standard.model.Charge;
import com.zy.app.rating.standard.model.LocationResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Map;

import static com.zy.app.rating.standard.model.buillder.ChargeBuilder.aCharge;
import static com.zy.app.rating.standard.model.buillder.LocationBuilder.aLocation;
import static com.zy.app.rating.standard.model.buillder.LocationResponseBuilder.aLocationResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RatingDescriptionServiceImplTest {

    @Mock
    Map<RatingType, RatingPlugin> ratingPlugins;

    @InjectMocks
    RatingDescriptionServiceImpl ratingDescriptionService;

    private final LocationResponse locationResponse = aLocationResponse()
            .withVisitedLocations(Arrays.asList(aLocation()
                    .withName("Test1")
                    .build()))
            .build();

    @Before
    public void setUp() throws Exception {
        when(ratingPlugins.get(RatingType.VOICESTANDARD)).thenReturn(new VoiceStandard());
        when(ratingPlugins.get(RatingType.INITIAL)).thenReturn(new Initial());
    }

    @Test
    public void testNoPluginDescription() throws Exception {
        Charge charge = aCharge()
                .withRatingPlugin(RatingType.VOICESTANDARD)
                .build();

        String description = ratingDescriptionService.getRatingDescription(charge, locationResponse);
        assertThat(description, equalTo("Test1"));
    }

    @Test
    public void testWithPluginDescription() throws Exception {
        Charge charge = aCharge()
                .withRatingPlugin(RatingType.INITIAL)
                .build();

        String description = ratingDescriptionService.getRatingDescription(charge, locationResponse);
        assertThat(description, equalTo("Test1 (Initial)"));
    }

    @Test
    public void testSeveralVisitedLocations() throws Exception {
        LocationResponse locationResponse2Locations = aLocationResponse()
                .withVisitedLocations(Arrays.asList(
                        aLocation()
                                .withName("Test1")
                                .build(),
                        aLocation()
                                .withName("Test2")
                                .build()))
                .build();

        Charge charge = aCharge()
                .withRatingPlugin(RatingType.INITIAL)
                .build();

        String description = ratingDescriptionService.getRatingDescription(charge, locationResponse2Locations);
        assertThat(description, equalTo("Test1 Test2 (Initial)"));

    }
}