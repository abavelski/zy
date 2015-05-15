package com.zy.app.rating.main.plugin.rating.initial;

import com.zy.app.rating.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.model.RatingRequest;

public class Initial implements RatingPlugin {

    @Override
    public Double calculatePrice(RatingRequest request, Double rate) {
        return (request.getAmount()>0)?rate: Double.valueOf(0);
    }

}
