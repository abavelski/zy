package com.zy.app.rating.model.buillder;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.RatingResponse;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 20/07/15
 */
public class RatingResponseBuilder {
    List<ChargeLine> chargeLines;
    List<String> locationNames;
    RatingRequest ratingRequest;

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

    public RatingResponseBuilder withRatingRequest(RatingRequest ratingRequest) {
        this.ratingRequest = ratingRequest;
        return this;
    }

    public RatingResponseBuilder but() {
        return aRatingResponse().withChargeLines(chargeLines).withLocationNames(locationNames).withRatingRequest(ratingRequest);
    }

    public RatingResponse build() {
        RatingResponse ratingResponse = new RatingResponse();
        ratingResponse.setChargeLines(chargeLines);
        ratingResponse.setLocationNames(locationNames);
        ratingResponse.setRatingRequest(ratingRequest);
        return ratingResponse;
    }
}
