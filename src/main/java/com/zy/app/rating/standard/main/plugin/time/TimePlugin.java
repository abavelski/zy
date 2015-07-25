package com.zy.app.rating.standard.main.plugin.time;

import com.zy.app.rating.standard.model.Charge;
import com.zy.app.rating.standard.model.TimePlanRequest;

import java.util.List;

public interface TimePlugin {

    List<Charge> getCharges(TimePlanRequest request);
    List<String> getCampaignCodes(TimePlanRequest request);

}
