package com.zy.app.rating.model.buillder;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.rating.model.RatingResponse;

import java.util.List;

/**
 * aba
 * 27/02/15
 */
public class RatingResponseBuilder {
    List<ChargeLine> chargeLines;
    List<String> locationNames;

    private RatingResponseBuilder() {
    }

    public static RatingResponseBuilder aRatingResponse() {
        return new RatingResponseBuilder();
    }

    public RatingResponseBuilder withChargeLines(List<ChargeLine> chargeLines) {
        this.chargeLines = chargeLines;
        return this;
    }

    public RatingResponseBuilder withLocationNames(List<String> locationNames) {
        this.locationNames = locationNames;
        return this;
    }

    public RatingResponseBuilder but() {
        return aRatingResponse().withChargeLines(chargeLines).withLocationNames(locationNames);
    }

    public RatingResponse build() {
        RatingResponse ratingResponse = new RatingResponse();
        ratingResponse.setChargeLines(chargeLines);
        ratingResponse.setLocationNames(locationNames);
        return ratingResponse;
    }
}
