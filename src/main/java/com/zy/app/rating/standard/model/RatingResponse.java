package com.zy.app.rating.standard.model;

import com.zy.app.common.model.ChargeLine;

import java.util.List;


public class RatingResponse {

    List<ChargeLine> chargeLines;
    RatingRequest ratingRequest;

    public List<ChargeLine> getChargeLines() {
        return chargeLines;
    }

    public void setChargeLines(List<ChargeLine> chargeLines) {
        this.chargeLines = chargeLines;
    }

    public RatingRequest getRatingRequest() {
        return ratingRequest;
    }

    public void setRatingRequest(RatingRequest ratingRequest) {
        this.ratingRequest = ratingRequest;
    }
}
