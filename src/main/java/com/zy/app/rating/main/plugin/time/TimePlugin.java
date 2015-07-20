package com.zy.app.rating.main.plugin.time;

import com.zy.app.rating.model.Charge;
import com.zy.app.rating.model.TimePlanRequest;

import java.util.List;

public interface TimePlugin {

    List<Charge> getCharges(TimePlanRequest request);
    List<String> getCampaignCodes(TimePlanRequest request);

}
