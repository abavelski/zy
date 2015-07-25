package com.zy.app.rating.standard.main;

import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;

import java.util.List;

/**
 * Created by aba on 24/02/15.
 */
public interface RatingService {

    RatingResponse rate(RatingRequest request);
    List<String> getCampaignCodes (RatingRequest request);

}
