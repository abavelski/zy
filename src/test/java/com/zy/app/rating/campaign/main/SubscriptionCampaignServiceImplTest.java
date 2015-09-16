package com.zy.app.rating.campaign.main;

import com.zy.app.rating.campaign.dao.SubscriptionCampaignDao;
import com.zy.app.rating.campaign.model.SubscriptionCampaign;
import com.zy.app.rating.standard.main.RatingService;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;
import org.junit.Before;
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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
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

    private final SubscriptionCampaign sc = aSubscriptionCampaign()
            .withSubscriptionId(1)
            .withCampaignCode("c1")
            .withCampaignPlugin(CampaignType.BUNDLE)
            .build();

    private final RatingRequest ratingRequest = aRatingRequest()
            .withPricePlanCode("pp1")
            .withSubscriptionId(1)
            .build();

    @Before
    public void setUp() throws Exception {
        when(campaignPlugins.get(CampaignType.BUNDLE)).thenReturn(bundle);
        when(ratingService.getCampaignCodes(ratingRequest)).thenReturn(Arrays.asList("c1", "c2"));
        when(subscriptionCampaignDao.getSubscriptionCampaignsForSubscription(1)).thenReturn(Arrays.asList(sc));
    }

    @Test
    public void testSignupToCampaign() throws Exception {
        when(subscriptionCampaignDao.createSubscriptionCampaign(sc)).thenReturn(23);

        service.signupToCampaign(aCampaignSignupRequest()
                .withCampaignCode("c1")
                .withCampaignType(CampaignType.BUNDLE)
                .withSubscriptionId(1)
                .build());

        verify(subscriptionCampaignDao).createSubscriptionCampaign(sc);
        verify(bundle).createNew(23, "c1");
    }

    @Test
    public void testRate() throws Exception {
        RatingResponse ratingResponse = service.rate(ratingRequest);
        verify(bundle).rate(ratingRequest, sc);
        assertThat(ratingResponse.getRatingRequest(), equalTo(ratingRequest));
    }

    @Test
    public void testEstimate() throws Exception {
        RatingResponse ratingResponse = service.estimate(ratingRequest);
        verify(bundle).estimate(ratingRequest, sc);
        assertThat(ratingResponse.getRatingRequest(), equalTo(ratingRequest));
    }
}