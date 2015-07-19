package com.zy.app.campaign.main;

import com.zy.app.campaign.dao.SubscriptionCampaignDao;
import com.zy.app.campaign.model.CampaignSignupRequest;
import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

import static com.zy.app.campaign.model.builder.SubscriptionCampaignBuilder.aSubscriptionCampaign;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
@Component
public class SubscriptionCampaignServiceImpl implements SubscriptionCampaignService {

    @Resource
    Map<CampaignType, CampaignPlugin> campaignPlugins;

    @Autowired
    SubscriptionCampaignDao subscriptionCampaignDao;

    @Override
    @Transactional
    public void signupToCampaign(CampaignSignupRequest request) {
        int id = subscriptionCampaignDao.createSubscriptionCampaign(aSubscriptionCampaign()
                .withSubscriptionId(request.getSubscriptionId())
                .withCampaignCode(request.getCampaignCode())
                .withCampaignPlugin(request.getCampaignType())
                .build());

        CampaignPlugin plugin = campaignPlugins.get(request.getCampaignType());
        plugin.createNew(id, request.getCampaignCode());
    }

    @Override
    public RatingResponse rate(RatingRequest request) {
        return null;
    }
}
