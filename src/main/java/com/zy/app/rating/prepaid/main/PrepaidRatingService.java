package com.zy.app.rating.prepaid.main;

import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;

/**
 * alexei.bavelski@gmail.com
 * 11/09/15
 */
public interface PrepaidRatingService {

    PrepaidRatingResponse startRatingSession(RatingRequest request);
    PrepaidRatingResponse updateRatingSession(long usedUnits, RatingRequest request);
    RatingResponse terminateRatingSession(RatingRequest request);

}
