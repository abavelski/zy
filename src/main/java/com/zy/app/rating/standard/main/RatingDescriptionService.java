package com.zy.app.rating.standard.main;

import com.zy.app.rating.standard.model.Charge;
import com.zy.app.rating.standard.model.LocationResponse;

/**
 * alexei.bavelski@gmail.com
 * 25/07/15
 */
public interface RatingDescriptionService {

    String getRatingDescription(Charge charge, LocationResponse locationResponse);

}
