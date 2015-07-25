package com.zy.app.rating.standard.main.plugin.rating;

import com.zy.app.rating.standard.model.RatingRequest;

public interface RatingPlugin {

    public Double calculatePrice(RatingRequest request, Double rate);
    public String getDescriptionForInvoice();

}
