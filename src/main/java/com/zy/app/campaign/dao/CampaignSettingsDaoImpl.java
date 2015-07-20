package com.zy.app.campaign.dao;

import com.zy.app.campaign.main.CampaignType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
@Component
public class CampaignSettingsDaoImpl implements CampaignSettingsDao {

    @Value("${config.path}")
    private String configPath;

    @Override
    public <T>T readCampaignSettings(CampaignType type, String code, Class<T> settingsClass) {

        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(new FileInputStream(configPath+"/campaigns/"+type.toString().toLowerCase()+"/"+code.toLowerCase()+ ".yaml"), settingsClass);
        } catch (Exception e) {
            throw new RuntimeException("unable to load fee", e);
        }

    }
}
