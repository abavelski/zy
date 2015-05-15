package com.zy.app.rating.main.plugin.location.flatrate;


import com.zy.app.rating.model.LocationResponse;
import com.zy.app.rating.model.Location;
import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.TimePlan;
import com.zy.app.rating.model.TrafficPlan;
import org.junit.Test;

import java.util.Arrays;

import static com.zy.app.rating.model.buillder.LocationBuilder.aLocation;
import static com.zy.app.rating.model.buillder.RatingRequestBuilder.aRatingRequest;
import static com.zy.app.rating.model.buillder.TimePlanBuilder.aTimePlan;
import static com.zy.app.rating.model.buillder.TrafficPlanBuilder.aTrafficPlan;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

public class FlatRateTest {

    @Test
    public void testVisitedLocation() {
        FlatRate flatRate = new FlatRate();

        RatingRequest request = aRatingRequest()
                .build();

        TimePlan timePlan = aTimePlan()
                .withCode("tp1")
                .build();

        Location location = aLocation()
                .withName("test1")
                .withTimePlans(Arrays.asList(timePlan))
                .build();

        TrafficPlan plan = aTrafficPlan()
                .withRootLocation(location)
                .build();

        LocationResponse locationResponse = flatRate.getLocationResponse(request, plan);
        assertThat("locations response is not null", locationResponse, is(not(nullValue())));
        assertThat("only one time plan is returned", locationResponse.getTimePlans().size(), equalTo(1));
        assertThat("expected time plan is returned", locationResponse.getTimePlans().get(0), equalTo(timePlan));
        assertThat("one location is returned", locationResponse.getVisitedLocations().size(), equalTo(1));
        assertThat("visited location is test1", locationResponse.getVisitedLocations().get(0), equalTo(location));
    }
}