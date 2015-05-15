package com.zy.app.rating.main.plugin.time.advanced;

import com.zy.app.rating.model.TimePlanRequest;
import com.zy.app.rating.main.plugin.time.TimePlugin;
import com.zy.app.rating.model.Charge;
import com.zy.app.rating.model.TimePlan;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class AdvancedTimePlugin implements TimePlugin {


    public static final String HOLIDAY = "hol";
    public static final String DEFAULT = "def";
    private Map<String, List<TimePlan>> timePlansMap = new HashMap<>();

    private boolean isHoliday(LocalDateTime chargeDate) {
        return false;
    }

    @Override
    public List<Charge> getCharges(TimePlanRequest request) {
        return getSelectedPlan(request).getCharges();
    }

    private TimePlan getSelectedPlan(TimePlanRequest request) {
        initTimePlansMap(request.getTimePlans());
        List<TimePlan> timePlans=null;
        if (isHoliday(request.getChargeDate()) ) {
            timePlans = timePlansMap.get(HOLIDAY);
        }
        if (timePlans==null) {
            String day =  new SimpleDateFormat("EEE", Locale.ENGLISH).format(request.getChargeDate());
            timePlans =  timePlansMap.get(day.toLowerCase());
        }
        if (timePlans==null) {
            timePlans = timePlansMap.get(DEFAULT);
        }
        if (timePlans == null) {
            throw  new RuntimeException("Time plan not found");
        }
        TimePlan selectedPlan = null;
        for (TimePlan timePlan : timePlans) {
            int hour = Integer.parseInt(new SimpleDateFormat("HH").format(request.getChargeDate()));
            if (timePlan.getStartHour()<=hour &&  hour<=timePlan.getEndHour()) {
                selectedPlan = timePlan;
                break;
            }
        }
        if (selectedPlan==null) {
            throw new RuntimeException("Time plan not found");
        }
        return selectedPlan;
    }

    private void initTimePlansMap(List<TimePlan> timePlans) {
        for (TimePlan timePlan : timePlans) {
            List<TimePlan> plans = timePlansMap.get(timePlan.getCode());
            if (plans==null) {
                plans = new ArrayList<>();
                timePlansMap.put(timePlan.getCode(), plans);
            }
            plans.add(timePlan);
        }
    }


}
