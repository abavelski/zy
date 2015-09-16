package com.zy.app.rating.standard.main.plugin.rating;

import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;

public interface RatingPlugin {

    PrepaidRatingResponse estimate(double maxPrice, long requestedUnits, double rate);
    double calculatePrice(long units, double rate);
    String getDescriptionForInvoice();

}
