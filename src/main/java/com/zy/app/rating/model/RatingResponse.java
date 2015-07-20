package com.zy.app.rating.model;

import com.zy.app.common.model.ChargeLine;

import java.util.List;


public class RatingResponse {

    List<ChargeLine> chargeLines;
    List<String> locationNames;
    RatingRequest ratingRequest;

    public List<ChargeLine> getChargeLines() {
        return chargeLines;
    }

    public void setChargeLines(List<ChargeLine> chargeLines) {
        this.chargeLines = chargeLines;
    }

    public List<String> getLocationNames() {
        return locationNames;
    }

    public void setLocationNames(List<String> locationNames) {
        this.locationNames = locationNames;
    }

    public RatingRequest getRatingRequest() {
        return ratingRequest;
    }

    public void setRatingRequest(RatingRequest ratingRequest) {
        this.ratingRequest = ratingRequest;
    }
}
