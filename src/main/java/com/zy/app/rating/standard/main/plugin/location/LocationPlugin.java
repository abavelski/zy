package com.zy.app.rating.standard.main.plugin.location;

import com.zy.app.rating.standard.model.LocationResponse;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.TrafficPlan;

public interface LocationPlugin {

    public LocationResponse getLocationResponse(RatingRequest request, TrafficPlan plan);



}
