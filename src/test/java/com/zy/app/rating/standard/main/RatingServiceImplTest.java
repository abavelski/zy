package com.zy.app.rating.standard.main;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.rating.standard.dao.PricePlanDao;
import com.zy.app.rating.standard.main.plugin.location.LocationPlugin;
import com.zy.app.rating.standard.main.plugin.location.LocationType;
import com.zy.app.rating.standard.main.plugin.location.flatrate.FlatRate;
import com.zy.app.rating.standard.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.standard.main.plugin.rating.RatingType;
import com.zy.app.rating.standard.main.plugin.rating.voice.VoiceStandard;
import com.zy.app.rating.standard.main.plugin.time.TimePlugin;
import com.zy.app.rating.standard.main.plugin.time.TimePluginType;
import com.zy.app.rating.standard.main.plugin.time.flat.FlatTimePlugin;
import com.zy.app.rating.standard.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import static com.zy.app.common.model.builder.ChargeLineBuilder.aChargeLine;
import static com.zy.app.rating.standard.model.buillder.ChargeBuilder.aCharge;
import static com.zy.app.rating.standard.model.buillder.LocationBuilder.aLocation;
import static com.zy.app.rating.standard.model.buillder.LocationResponseBuilder.aLocationResponse;
import static com.zy.app.rating.standard.model.buillder.PricePlanBuilder.aPricePlan;
import static com.zy.app.rating.standard.model.buillder.RatingRequestBuilder.aRatingRequest;
import static com.zy.app.rating.standard.model.buillder.TimePlanBuilder.aTimePlan;
import static com.zy.app.rating.standard.model.buillder.TrafficPlanBuilder.aTrafficPlan;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RatingServiceImplTest {

    @InjectMocks
    RatingServiceImpl ratingService;

    @Mock
    RatingDescriptionService ratingDescriptionService;
    @Mock
    PricePlanDao pricePlanDao;
    @Mock
    TrafficPlanService trafficPlanService;
    @Mock
    Map<LocationType, LocationPlugin> locationPlugins;
    @Mock
    Map<TimePluginType, TimePlugin> timePlugins;
    @Mock
    Map<RatingType, RatingPlugin> ratingPlugins;



    @Test
    public void testFlatPricePlan() {
        Charge charge = aCharge()
                    .withRate(0.29)
                    .withRatingPlugin(RatingType.VOICESTANDARD)
                .build();

        TimePlan timePlan = aTimePlan()
                    .withCharges(Arrays.asList(charge))
                .build();

        Location location = aLocation()
                    .withTimePlans(Arrays.asList(timePlan))
                .build();

        TrafficPlan tp = aTrafficPlan()
                    .withTimePlugin(TimePluginType.FLAT)
                    .withLocationPlugin(LocationType.FLAT)
                    .withRatingCode("int-voice")
                    .withRootLocation(location)
                .build();

        PricePlan pp = aPricePlan()
                    .withCode("pp1")
                    .withTrafficPlans(Arrays.asList(tp))
                .build();

        LocationResponse locationsResponse = aLocationResponse()
                .withVisitedLocations(Arrays.asList(location))
                .withTimePlans(Arrays.asList(timePlan))
                .build();

        when(pricePlanDao.getPricePlanByCode("pp1")).thenReturn(pp);
        when(trafficPlanService.getTrafficPlanByRatingCode(pp, "int-voice")).thenReturn(tp);
        when(locationPlugins.get(LocationType.FLAT)).thenReturn(new FlatRate());
        when(timePlugins.get(TimePluginType.FLAT)).thenReturn(new FlatTimePlugin());
        when(ratingPlugins.get(RatingType.VOICESTANDARD)).thenReturn(new VoiceStandard());
        when(ratingDescriptionService.getRatingDescription(charge, locationsResponse)).thenReturn("VOICESTANDARD");

        LocalDateTime now = LocalDateTime.now();
        RatingRequest request = aRatingRequest()
                    .withPricePlanCode("pp1")
                    .withRatingCode("int-voice")
                    .withAmount(61)
                    .withChargeDate(now)
                .build();

        RatingResponse response = ratingService.rate(request);

        ChargeLine chargeLine = aChargeLine()
                    .withTotal(0.58d)
                    .withChargeDate(now)
                    .withDescription("VOICESTANDARD")
                .build();

        assertThat(response.getChargeLines(), notNullValue());
        assertThat(response.getChargeLines().size(), equalTo(1));
        assertThat(response.getChargeLines().get(0), equalTo(chargeLine));
    }
}