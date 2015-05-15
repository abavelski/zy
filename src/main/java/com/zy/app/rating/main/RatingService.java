package com.zy.app.rating.main;

import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.RatingResponse;

/**
 * Created by aba on 24/02/15.
 */
public interface RatingService {

    RatingResponse rate(RatingRequest request);

}
