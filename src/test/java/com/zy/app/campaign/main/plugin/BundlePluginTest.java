package com.zy.app.campaign.main.plugin;

import com.zy.app.campaign.dao.BundleDao;
import com.zy.app.campaign.dao.CampaignSettingsDao;
import com.zy.app.campaign.main.CampaignType;
import com.zy.app.campaign.model.Bundle;
import com.zy.app.campaign.model.BundleSettings;
import com.zy.app.common.main.UtilService;
import com.zy.app.rating.model.RatingResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.zy.app.campaign.main.plugin.BundlePluginTestData.*;
import static com.zy.app.common.model.ChargeLineBuilder.aChargeLine;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BundlePluginTest {

    @Mock
    CampaignSettingsDao campaignSettingsDao;
    @Mock
    BundleDao bundleDao;
    @Mock
    UtilService utilService;

    @InjectMocks
    BundlePlugin bundlePlugin;

    @Before
    public void setUp() throws Exception {
        when(campaignSettingsDao.readCampaignSettings(CampaignType.BUNDLE, "voice600", BundleSettings.class))
                .thenReturn(bundleSettings());

        when(bundleDao.getBundleBySubscriptionCampaignIdAndCampaignCode(1, "voice600"))
                .thenReturn(bundleWith540SecondsLeft());
    }

    @Test
    public void testRate10sBundleUpdated() throws Exception {
        bundlePlugin.rate(ratingRequest.but().withAmount(10).build(), subscriptionCampaign());
        verify(bundleDao).updateBundle(bundleWith480SecondsLeft());
    }

    @Test
    public void testRate60sBundleUpdated() {
        bundlePlugin.rate(ratingRequest.but().withAmount(60).build(), subscriptionCampaign());
        verify(bundleDao).updateBundle(bundleWith480SecondsLeft());
    }

    @Test
    public void testRate78sBundleUpdated() {
        bundlePlugin.rate(ratingRequest.but().withAmount(78).build(), subscriptionCampaign());
        verify(bundleDao).updateBundle(voice600.but().withRemainingAmount(420).build());
    }

    @Test
    public void testRate0sBundleNotUpdated() {
        bundlePlugin.rate(ratingRequest.but().withAmount(0).build(), subscriptionCampaign());
        verify(bundleDao, never()).updateBundle(any(Bundle.class));
    }

    @Test
    public void testRate0sReturnsNoChargeLinesAndNoRequest() {
        RatingResponse response = bundlePlugin.rate(ratingRequest.but().withAmount(0).build(), subscriptionCampaign());
        assertThat("rating response is not null", response, notNullValue());
        assertThat("no charge lines", response.getChargeLines().size(), equalTo(0));
        assertThat("request is null", response.getRatingRequest(), nullValue());
    }

    @Test
    public void testRate10sReturnsChargeLine() {
        RatingResponse response = bundlePlugin.rate(ratingRequest.but().withAmount(10).build(), subscriptionCampaign());
        assertThat("1 charge lines", response.getChargeLines().size(), equalTo(1));
        assertThat(response.getChargeLines().get(0), equalTo(
                aChargeLine()
                    .withSubscriptionId(1)
                    .withDescription("test")
                    .withTotal(0d)
                    .withChargeDate(CHARGEDATE)
                    .build()));
    }

    @Test
    public void testRate620sReturnsChargeLineAndNewRequest() {
        RatingResponse response = bundlePlugin.rate(ratingRequest.but().withAmount(620).build(), subscriptionCampaign());
        assertThat("1 charge lines", response.getChargeLines().size(), equalTo(1));
        assertThat(response.getChargeLines().get(0), equalTo(
                aChargeLine()
                        .withSubscriptionId(1)
                        .withDescription("test")
                        .withTotal(0d)
                        .withChargeDate(CHARGEDATE)
                        .build()));

        verify(bundleDao).updateBundle(voice600.but().withRemainingAmount(0).build());
        assertThat(response.getRatingRequest(), equalTo(ratingRequest.but().withAmount(80).build()));

    }



}