package com.zy.app.rating.main.plugin.rating;

import com.zy.app.rating.model.RatingRequest;

public interface RatingPlugin {

    public Double calculatePrice(RatingRequest request, Double rate);

}
