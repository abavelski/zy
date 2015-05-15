package com.zy.app.rating.main.plugin.location;

import com.zy.app.rating.model.LocationResponse;
import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.TrafficPlan;

public interface LocationPlugin {

    public LocationResponse getLocationResponse(RatingRequest request, TrafficPlan plan);



}
