package com.zy.app.rating.campaign.main;

import com.zy.app.rating.campaign.dao.SubscriptionCampaignDao;
import com.zy.app.rating.campaign.model.SubscriptionCampaign;
import com.zy.app.rating.standard.main.RatingService;
import com.zy.app.rating.standard.model.RatingRequest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Map;

import static com.zy.app.rating.campaign.model.builder.CampaignSignupRequestBuilder.aCampaignSignupRequest;
import static com.zy.app.rating.campaign.model.builder.SubscriptionCampaignBuilder.aSubscriptionCampaign;
import static com.zy.app.rating.standard.model.buillder.RatingRequestBuilder.aRatingRequest;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionCampaignServiceImplTest {

    @InjectMocks
    SubscriptionCampaignServiceImpl service;
    @Mock
    RatingService ratingService;
    @Mock
    SubscriptionCampaignDao subscriptionCampaignDao;
    @Mock
    Map<CampaignType, CampaignPlugin> campaignPlugins;
    @Mock
    CampaignPlugin bundle;

    @Test
    public void testSignupToCampaign() throws Exception {
        SubscriptionCampaign sc = aSubscriptionCampaign()
                .withSubscriptionId(1)
                .withCampaignCode("c1")
                .withCampaignPlugin(CampaignType.BUNDLE)
                .build();

        when(subscriptionCampaignDao.createSubscriptionCampaign(sc)).thenReturn(23);
        when(campaignPlugins.get(CampaignType.BUNDLE)).thenReturn(bundle);

        service.signupToCampaign(aCampaignSignupRequest()
                .withCampaignCode("c1")
                .withCampaignType(CampaignType.BUNDLE)
                .withSubscriptionId(1)
                .build());

        verify(subscriptionCampaignDao).createSubscriptionCampaign(sc);
        verify(bundle).createNew(23, "c1");
    }

    @Ignore
    @Test
    public void testRate() throws Exception {

        RatingRequest request = aRatingRequest()
                .withPricePlanCode("pp1")
                .withRatingCode("int-voice")
                .withAmount(61)
                //.withChargeDate(now)
                .build();

        when(ratingService.getCampaignCodes(request)).thenReturn(Arrays.asList("c1", "c2"));

        service.rate(request);

    }
}