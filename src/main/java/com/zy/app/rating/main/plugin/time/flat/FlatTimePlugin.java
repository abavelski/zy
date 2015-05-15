package com.zy.app.rating.main.plugin.time.flat;

import com.zy.app.rating.model.TimePlanRequest;
import com.zy.app.rating.main.plugin.time.TimePlugin;
import com.zy.app.rating.model.Charge;
import com.zy.app.rating.model.TimePlan;

import java.util.List;

public class FlatTimePlugin implements TimePlugin {

    @Override
    public List<Charge> getCharges(TimePlanRequest request) {
        List<TimePlan> timePlans = request.getTimePlans();
        if (timePlans==null || timePlans.size()!=1) {
            throw new RuntimeException("Time plan not found.");
        }
        return timePlans.get(0).getCharges();
    }

}