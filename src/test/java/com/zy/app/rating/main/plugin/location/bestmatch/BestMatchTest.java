package com.zy.app.rating.main.plugin.location.bestmatch;

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

public class BestMatchTest {

    @Test
    public void testVisitedLocations() {
        BestMatch bestMatch = new BestMatch();

        RatingRequest request = aRatingRequest()
                    .withDestination("044123")
                .build();

        TimePlan timePlan = aTimePlan()
                    .withCode("tp1")
                .build();

        Location grandchild1 = aLocation()
                    .withName("grandchild1")
                    .withTimePlans(Arrays.asList(timePlan))
                    .withSubNumber("123")
                .build();

        Location child1 = aLocation()
                    .withName("child1")
                    .withTimePlans(Arrays.asList(timePlan))
                    .withSubNumber("044")
                    .withChildren(Arrays.asList(grandchild1))
                .build();

        Location location = aLocation()
                    .withChildren(Arrays.asList(child1))
                    .withName("root")
                .build();

        TrafficPlan plan = aTrafficPlan()
                    .withRootLocation(location)
                .build();

        LocationResponse locationResponse = bestMatch.getLocationResponse(request, plan);

        assertThat("locations response is not null", locationResponse, is(not(nullValue())));
        assertThat("only one time plan is returned", locationResponse.getTimePlans().size(), equalTo(1));
        assertThat("expected time plan is returned", locationResponse.getTimePlans().get(0), equalTo(timePlan));
        assertThat("one location is returned", locationResponse.getVisitedLocations().size(), equalTo(3));
        assertThat("visited location 1 is root", locationResponse.getVisitedLocations().get(0), equalTo(location));
        assertThat("visited location 2 is child1", locationResponse.getVisitedLocations().get(1), equalTo(child1));
        assertThat("visited location 3 is grandchild1", locationResponse.getVisitedLocations().get(2), equalTo(grandchild1));

    }
}