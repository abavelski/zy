package com.zy.app.rating.standard.main;

import com.zy.app.rating.standard.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.standard.main.plugin.rating.RatingType;
import com.zy.app.rating.standard.model.Charge;
import com.zy.app.rating.standard.model.Location;
import com.zy.app.rating.standard.model.LocationResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * alexei.bavelski@gmail.com
 * 25/07/15
 */
@Component
public class RatingDescriptionServiceImpl implements RatingDescriptionService {

    @Resource
    Map<RatingType, RatingPlugin> ratingPlugins;

    @Override
    public String getRatingDescription(Charge charge, LocationResponse locationResponse) {

        RatingPlugin ratingPlugin = ratingPlugins.get(charge.getRatingPlugin());

        StringBuilder description = new StringBuilder(getLocationsDescriptions(locationResponse));
        if (!"".equals(ratingPlugin.getDescriptionForInvoice())) {
            description.append(" (")
                    .append(ratingPlugin.getDescriptionForInvoice())
                    .append(")");
        }
        return description.toString();
    }

    private String getLocationsDescriptions(LocationResponse response) {
        StringBuilder resp = new StringBuilder();
        for (Location location : response.getVisitedLocations()) {
            resp.append(location.getName());
            resp.append(" ");
        }
        return  resp.toString().trim();
    }

}
