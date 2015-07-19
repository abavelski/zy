package com.zy.app.campaign.dao;

import com.zy.app.campaign.main.CampaignType;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public interface CampaignSettingsDao {

    <T> T readCampaignSettings(CampaignType type, String code, Class<T> settingsClass);

}
