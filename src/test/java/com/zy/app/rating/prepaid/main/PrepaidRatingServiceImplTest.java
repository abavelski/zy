package com.zy.app.rating.prepaid.main;

import com.zy.app.rating.campaign.main.SubscriptionCampaignService;
import com.zy.app.rating.prepaid.dao.BalanceDao;
import com.zy.app.rating.prepaid.dao.RatingSessionDao;
import com.zy.app.rating.prepaid.model.Balance;
import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;
import com.zy.app.rating.prepaid.model.RatingSession;
import com.zy.app.rating.prepaid.model.builder.BalanceBuilder;
import com.zy.app.rating.prepaid.model.builder.RatingSessionBuilder;
import com.zy.app.rating.standard.main.RatingService;
import com.zy.app.rating.standard.model.RatingRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.zy.app.rating.RatingTestData.CHARGEDATE;
import static com.zy.app.rating.RatingTestData.ratingRequest;
import static com.zy.app.rating.prepaid.model.builder.BalanceBuilder.aBalance;
import static com.zy.app.rating.prepaid.model.builder.PrepaidRatingResponseBuilder.aPrepaidRatingResponse;
import static com.zy.app.rating.prepaid.model.builder.RatingSessionBuilder.aRatingSession;
import static com.zy.app.rating.standard.model.buillder.RatingResponseBuilder.aRatingResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * alexei.bavelski@gmail.com
 * 13/09/15
 */
@RunWith(MockitoJUnitRunner.class)
public class PrepaidRatingServiceImplTest {

    @InjectMocks
    PrepaidRatingServiceImpl prepaidRatingService;

    @Mock
    SubscriptionCampaignService subscriptionCampaignService;
    @Mock
    RatingSessionDao ratingSessionDao;
    @Mock
    BalanceDao balanceDao;
    @Mock
    RatingService ratingService;

    private static final BalanceBuilder BALANCE = aBalance()
            .withId(1)
            .withSubscriptionId(1)
            .withReservedAmount(0)
            .withAmount(100d);

    private static final RatingSessionBuilder RATING_SESSION  = aRatingSession()
            .withSessionKey("123")
            .withChargeDate(CHARGEDATE)
            .withPrice(0d)
            .withReservedUnits(61L)
            .withUsedUnits(0L);

    @Test
    public void testStartRatingSessionFullyGrantedByCampaign() {
        RatingRequest ratingRequest = ratingRequest().but().withSessionKey("123").build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withGrantedUnits(61L)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);

        assertThat("granted 61 units", response.getGrantedUnits(), equalTo(61L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));
        verify(ratingSessionDao).createSession(RATING_SESSION.build());
    }

    @Test
    public void testStartRatingSessionGrantedByStandardRating() {
        RatingRequest ratingRequest = ratingRequest().but().withSessionKey("123").build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(ratingRequest)
                .withGrantedUnits(0L)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(BALANCE.build());
        when(ratingService.estimate(100d, ratingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                .withGrantedUnits(61)
                .withRemainingBalance(99.02d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 61 units", response.getGrantedUnits(), equalTo(61L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));

        verify(ratingSessionDao).createSession(RATING_SESSION.but().withPrice(0.98d).build());
        verify(balanceDao).updateBalance(BALANCE.but().withReservedAmount(0.98d).build());
    }

    @Test
    public void testStartRatingSessionPartiallyGranted() {
        RatingRequest ratingRequest = ratingRequest().but().withSessionKey("123").withUnits(135).build();
        RatingRequest remainingRequest = ratingRequest().but().withSessionKey("123").withUnits(75L).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(remainingRequest)
                .withGrantedUnits(60L)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(BALANCE.but().withAmount(5d).build());
        when(ratingService.estimate(5d, remainingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.PARTIALLY_GRANTED)
                .withGrantedUnits(60L)
                .withRemainingBalance(2d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 120 units", response.getGrantedUnits(), equalTo(120L));
        assertThat("status is PARTIALLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.PARTIALLY_GRANTED));

        verify(ratingSessionDao).createSession(RATING_SESSION.but().withPrice(3d).withReservedUnits(120L).build());
        verify(balanceDao).updateBalance(BALANCE.but().withAmount(5d).withReservedAmount(3d).build());
    }

    @Test
    public void testStartRatingSessionPartiallyByCampaignNoMoneyOnTheBalance() {
        RatingRequest ratingRequest = ratingRequest().but().withSessionKey("123").withUnits(135).build();
        RatingRequest remainingRequest = ratingRequest().but().withSessionKey("123").withUnits(75L).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(remainingRequest)
                .withGrantedUnits(60L)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(BALANCE.but().withAmount(2d).build());
        when(ratingService.estimate(2d, remainingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS)
                .withGrantedUnits(0L)
                .withRemainingBalance(2d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 60 units", response.getGrantedUnits(), equalTo(60L));
        assertThat("status is PARTIALLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.PARTIALLY_GRANTED));

        verify(ratingSessionDao).createSession(RATING_SESSION.but().withReservedUnits(60L).build());
        verify(balanceDao, never()).updateBalance(any(Balance.class));
    }

    @Test
    public void testStartSessionInsufficientFunds() {
        RatingRequest ratingRequest = ratingRequest().but().withSessionKey("123").withUnits(135).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(ratingRequest)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(BALANCE.but().withAmount(2d).build());
        when(ratingService.estimate(2d, ratingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS)
                .withGrantedUnits(0L)
                .withRemainingBalance(2d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 0 units", response.getGrantedUnits(), equalTo(0L));
        assertThat("status is INSUFFICIENT_FUNDS", response.getStatus(), equalTo(PrepaidRatingStatus.INSUFFICIENT_FUNDS));

        verify(ratingSessionDao, never()).createSession(any(RatingSession.class));
        verify(balanceDao, never()).updateBalance(any(Balance.class));
    }
}