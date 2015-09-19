package com.zy.app.rating.prepaid.main;

import com.zy.app.rating.campaign.main.SubscriptionCampaignService;
import com.zy.app.rating.prepaid.dao.BalanceDao;
import com.zy.app.rating.prepaid.dao.RatingSessionDao;
import com.zy.app.rating.prepaid.model.Balance;
import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;
import com.zy.app.rating.prepaid.model.RatingSession;
import com.zy.app.rating.standard.main.RatingService;
import com.zy.app.rating.standard.model.RatingRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.zy.app.rating.RatingTestData.*;
import static com.zy.app.rating.prepaid.model.builder.PrepaidRatingResponseBuilder.aPrepaidRatingResponse;
import static com.zy.app.rating.standard.model.buillder.RatingResponseBuilder.aRatingResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    public void testStartRatingSessionFullyGrantedByCampaign() {
        RatingRequest ratingRequest = ratingRequest().build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withGrantedUnits(61L)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);

        assertThat("granted 61 units", response.getGrantedUnits(), equalTo(61L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));
        verify(ratingSessionDao).createSession(ratingSession().build());
    }
    @Test
    public void testStartRatingSessionGrantedByStandardRating() {
        RatingRequest ratingRequest = ratingRequest().build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(ratingRequest)
                .withGrantedUnits(0L)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(balance().build());
        when(ratingService.estimate(100d, ratingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                .withGrantedUnits(61)
                .withRemainingBalance(99.02d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 61 units", response.getGrantedUnits(), equalTo(61L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));

        verify(ratingSessionDao).createSession(ratingSession().but().withPrice(0.98d).build());
        verify(balanceDao).updateBalance(balance().but().withReservedAmount(0.98d).build());
    }
    @Test
    public void testStartRatingSessionPartiallyGranted() {
        RatingRequest ratingRequest = ratingRequest().but().withUnits(135).build();
        RatingRequest remainingRequest = ratingRequest().but().withUnits(75L).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(remainingRequest)
                .withGrantedUnits(60L)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(balance().but().withAmount(5d).build());
        when(ratingService.estimate(5d, remainingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.PARTIALLY_GRANTED)
                .withGrantedUnits(60L)
                .withRemainingBalance(2d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 120 units", response.getGrantedUnits(), equalTo(120L));
        assertThat("status is PARTIALLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.PARTIALLY_GRANTED));

        verify(ratingSessionDao).createSession(ratingSession().but().withPrice(3d).withReservedUnits(120L).build());
        verify(balanceDao).updateBalance(balance().but().withAmount(5d).withReservedAmount(3d).build());
    }
    @Test
    public void testStartRatingSessionPartiallyByCampaignNoMoneyOnTheBalance() {
        RatingRequest ratingRequest = ratingRequest().but().withUnits(135).build();
        RatingRequest remainingRequest = ratingRequest().but().withUnits(75L).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(remainingRequest)
                .withGrantedUnits(60L)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(balance().but().withAmount(2d).build());
        when(ratingService.estimate(2d, remainingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS)
                .withGrantedUnits(0L)
                .withRemainingBalance(2d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 60 units", response.getGrantedUnits(), equalTo(60L));
        assertThat("status is PARTIALLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.PARTIALLY_GRANTED));

        verify(ratingSessionDao).createSession(ratingSession().but().withReservedUnits(60L).build());
        verify(balanceDao, never()).updateBalance(any(Balance.class));
    }
    @Test
    public void testStartSessionInsufficientFunds() {
        RatingRequest ratingRequest = ratingRequest().but().withUnits(135).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(ratingRequest)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(balance().but().withAmount(2d).build());
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
    @Test
    public void testUpdateRatingSessionFullyGrantedByCampaign() {
        RatingRequest ratingRequest = ratingRequest().but().withUnits(30).build();

        when(subscriptionCampaignService.estimate(ratingRequest().but().withUnits(91).withSessionKey("123").build()))
                .thenReturn(aRatingResponse().withGrantedUnits(91L).build());

        when(ratingSessionDao.findSession("123")).thenReturn(ratingSession().build());

        PrepaidRatingResponse response = prepaidRatingService.updateRatingSession(61L, ratingRequest);
        assertThat("granted 30 units", response.getGrantedUnits(), equalTo(30L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));
        verify(ratingSessionDao).updateSession(ratingSession().but().withUsedUnits(61L).withReservedUnits(30L).build());
    }
    @Test
    public void testUpdateRatingSessionPartlyUsedFullyGrantedByCampaign() {
        when(ratingSessionDao.findSession("123")).thenReturn(ratingSession().build());
        RatingRequest ratingRequest = ratingRequest().but().withUnits(35).build();
        when(subscriptionCampaignService.estimate(ratingRequest().but().withUnits(96).build()))
                .thenReturn(aRatingResponse().withGrantedUnits(91).build());

        PrepaidRatingResponse response = prepaidRatingService.updateRatingSession(45L, ratingRequest);
        assertThat("granted 30 units", response.getGrantedUnits(), equalTo(35L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));
        verify(ratingSessionDao).updateSession(ratingSession().but().withUsedUnits(45L).withReservedUnits(51L).build());
    }
    @Test
    public void testUpdateRatingSessionGrantedByStandardRating() {
        RatingRequest ratingRequest = ratingRequest().but().withUnits(96).build();

        when(ratingSessionDao.findSession("123")).thenReturn(ratingSession().withPrice(0.98).build());
        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(ratingRequest)
                .withGrantedUnits(0L)
                .build());

        when(balanceDao.findBalanceBySubscriptionId(1)).thenReturn(balance().build());
        when(ratingService.estimate(100d, ratingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                .withGrantedUnits(96)
                .withRemainingBalance(99.02d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.updateRatingSession(61, ratingRequest().but().withUnits(35).build());
        assertThat("granted 35 units", response.getGrantedUnits(), equalTo(35L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));

        verify(ratingService).estimate(100d, ratingRequest);
        verify(ratingSessionDao).updateSession(ratingSession().but().withUsedUnits(61).withReservedUnits(35).withPrice(0.98).build());
        verify(balanceDao).updateBalance(balance().but().withReservedAmount(0.98).build());
    }
}