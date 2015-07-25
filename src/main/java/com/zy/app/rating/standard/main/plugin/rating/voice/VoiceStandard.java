package com.zy.app.rating.standard.main.plugin.rating.voice;

import com.zy.app.rating.standard.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.standard.model.RatingRequest;

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

    @Override
    public String getDescriptionForInvoice() {
        return "";
    }

}
