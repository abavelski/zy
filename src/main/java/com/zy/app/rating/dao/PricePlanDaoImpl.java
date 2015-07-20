package com.zy.app.rating.dao;

import com.zy.app.rating.model.PricePlan;
import com.zy.app.rating.model.TrafficMapping;
import com.zy.app.rating.model.TrafficMappings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static com.zy.app.rating.model.buillder.PricePlanBuilder.aPricePlan;

/**
 * aba
 * 14/03/15
 */
@Component
public class PricePlanDaoImpl implements PricePlanDao {

    @Value("${config.path}")
    private String configPath;

    @Override
    public PricePlan getCampaignPlanByCode(String code) {
        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(new FileInputStream(configPath+"/campaignplans/"+code+ ".yaml"), PricePlan.class);
        } catch (Exception e) {
            throw new RuntimeException("unable to load price plan", e);
        }
    }

    @Override
    public PricePlan getPricePlanByCode(String code) {
        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(new FileInputStream(configPath+"/priceplans/"+code+ ".yaml"), PricePlan.class);
        } catch (Exception e) {
            throw new RuntimeException("unable to load price plan", e);
        }
    }

    @Override
    public List<TrafficMapping> getTrafficMappingsForPricePlan(String code) {
        Yaml yaml = new Yaml();
        try {
            TrafficMappings mappings  = yaml.loadAs(new FileInputStream(configPath + "/mappings/" + code + ".yaml"), TrafficMappings.class);
            return mappings.getTrafficMappings();
        } catch (Exception e) {
            throw new RuntimeException("unable to load traffic mapping for price plan "+code, e);
        }
    }

    @Override
    public List<String> getAllPricePlans() {
        List<String> pricePlans = new ArrayList<>();
        File[] files = new File(configPath+"/priceplans").listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".yaml")) {
                pricePlans.add(file.getName().replace(".yaml", ""));
            }
        }
        return pricePlans;
    }
}
