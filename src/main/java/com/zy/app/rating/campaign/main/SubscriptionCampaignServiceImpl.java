package com.zy.app.rating.campaign.main;

import com.zy.app.rating.campaign.dao.SubscriptionCampaignDao;
import com.zy.app.rating.campaign.model.CampaignSignupRequest;
import com.zy.app.rating.campaign.model.SubscriptionCampaign;
import com.zy.app.rating.standard.main.RatingService;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zy.app.rating.campaign.model.builder.SubscriptionCampaignBuilder.aSubscriptionCampaign;
import static com.zy.app.rating.standard.model.buillder.RatingResponseBuilder.aRatingResponse;

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

    @Autowired
    RatingService ratingService;

    @Override
    @Transactional
    public void signupToCampaign(CampaignSignupRequest request) {

        int id = subscriptionCampaignDao.createSubscriptionCampaign(
                aSubscriptionCampaign()
                    .withSubscriptionId(request.getSubscriptionId())
                    .withCampaignCode(request.getCampaignCode())
                    .withCampaignPlugin(request.getCampaignType())
                    .build());

        CampaignPlugin plugin = campaignPlugins.get(request.getCampaignType());
        plugin.createNew(id, request.getCampaignCode());
    }

    @Override
    public void activateCampaignsForSubscription(Integer subscriptionId) {
        List<SubscriptionCampaign> campaigns = subscriptionCampaignDao.getSubscriptionCampaignsForSubscription(subscriptionId);
        for (SubscriptionCampaign subscriptionCampaign : campaigns) {
            CampaignPlugin plugin = campaignPlugins.get(subscriptionCampaign.getCampaignPlugin());
            plugin.activate(subscriptionCampaign.getId(), subscriptionCampaign.getCampaignCode());
        }
    }

    @Override
    public RatingResponse rate(RatingRequest request) {
        RatingResponse response=null;
        List<String> campaignCodes = ratingService.getCampaignCodes(request);

        List<SubscriptionCampaign> campaigns = subscriptionCampaignDao.getSubscriptionCampaignsForSubscription(request.getSubscriptionId());
        for (SubscriptionCampaign campaign : campaigns) {
            if (campaignCodes.contains(campaign.getCampaignCode())) {
                CampaignPlugin plugin = campaignPlugins.get(campaign.getCampaignPlugin());
                response = plugin.rate(request, campaign);
            }
        }
        if (response==null) {
            response = aRatingResponse()
                    .withRatingRequest(request)
                    .withChargeLines(new ArrayList<>())
                    .build();
        }
        return response;
    }
}
