package com.zy.app.rating.main.plugin.rating.voice;

import com.zy.app.rating.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.model.RatingRequest;

public class VoiceStandard implements RatingPlugin {


    @Override
    public Double calculatePrice(RatingRequest request, Double rate) {
        if (request.getAmount()==0) {
            return Double.valueOf(0);
        }
        long rest = request.getAmount() % 60;
        long minutes = request.getAmount() / 60;
        return rate*minutes+((rest==0)?0:rate);
    }

}
