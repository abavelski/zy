package com.zy.rest.rating;

import com.zy.app.rating.standard.dao.PricePlanDao;
import com.zy.app.rating.standard.model.PricePlan;
import com.zy.app.rating.standard.model.TrafficMapping;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="price-plans", description = "Price plan operations")
@RequestMapping("/api/price-plans")
public class PricePlanController {

    @Autowired
    PricePlanDao pricePlanDao;

    @RequestMapping(value="/{code}", method = RequestMethod.GET, produces="application/json")
    public PricePlan getPricePlanByCode(@PathVariable String code) {
        return pricePlanDao.getPricePlanByCode(code);
    }

    @RequestMapping(value="/", method = RequestMethod.GET, produces="application/json")
    public List<String> getAllPricePlans() {
        return pricePlanDao.getAllPricePlans();
    }

    @RequestMapping(value="/{code}/traffic-mappings", method = RequestMethod.GET, produces="application/json")
    public List<TrafficMapping> getTrafficPlanMappings(@PathVariable String code) {
        return pricePlanDao.getTrafficMappingsForPricePlan(code);
    }


}
