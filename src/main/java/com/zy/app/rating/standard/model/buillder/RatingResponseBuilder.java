package com.zy.app.rating.standard.model.buillder;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 13/09/15
 */
public class RatingResponseBuilder {
    List<ChargeLine> chargeLines;RatingRequest ratingRequest;
    long grantedUnits;

    private RatingResponseBuilder() {
    }

    public static RatingResponseBuilder aRatingResponse() {
        return new RatingResponseBuilder();
    }

    public RatingResponseBuilder withChargeLines(List<ChargeLine> chargeLines) {
        this.chargeLines = chargeLines;
        return this;
    }

    public RatingResponseBuilder withRatingRequest(RatingRequest ratingRequest) {
        this.ratingRequest = ratingRequest;
        return this;
    }

    public RatingResponseBuilder withGrantedUnits(long grantedUnits) {
        this.grantedUnits = grantedUnits;
        return this;
    }

    public RatingResponseBuilder but() {
        return aRatingResponse().withChargeLines(chargeLines).withRatingRequest(ratingRequest).withGrantedUnits(grantedUnits);
    }

    public RatingResponse build() {
        RatingResponse ratingResponse = new RatingResponse();
        ratingResponse.setChargeLines(chargeLines);
        ratingResponse.setRatingRequest(ratingRequest);
        ratingResponse.setGrantedUnits(grantedUnits);
        return ratingResponse;
    }
}
