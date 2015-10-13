package com.zy.app.rating.prepaid.main;

import com.zy.app.rating.campaign.main.SubscriptionCampaignService;
import com.zy.app.rating.prepaid.dao.RatingSessionDao;
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

import static com.zy.app.rating.RatingTestData.ratingRequest;
import static com.zy.app.rating.RatingTestData.ratingSession;
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
    BalanceService balanceService;
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

        when(balanceService.getRemainingBalanceExclVat(1)).thenReturn(100d);
        when(ratingService.estimate(100d, ratingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                .withGrantedUnits(61)
                .withRemainingBalance(99.02d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 61 units", response.getGrantedUnits(), equalTo(61L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));

        verify(ratingSessionDao).createSession(ratingSession().but().withPrice(0.98d).build());
        verify(balanceService).reserveAmountExclVat(1, 0, 0.98d);
    }
    @Test
    public void testStartRatingSessionPartiallyGranted() {
        RatingRequest ratingRequest = ratingRequest().but().withUnits(135).build();
        RatingRequest remainingRequest = ratingRequest().but().withUnits(75L).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(remainingRequest)
                .withGrantedUnits(60L)
                .build());

        when(balanceService.getRemainingBalanceExclVat(1)).thenReturn(5d);
        when(ratingService.estimate(5d, remainingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.PARTIALLY_GRANTED)
                .withGrantedUnits(60L)
                .withRemainingBalance(2d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 120 units", response.getGrantedUnits(), equalTo(120L));
        assertThat("status is PARTIALLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.PARTIALLY_GRANTED));

        verify(ratingSessionDao).createSession(ratingSession().but().withPrice(3d).withReservedUnits(120L).build());
        verify(balanceService).reserveAmountExclVat(1, 0, 3d);
    }
    @Test
    public void testStartRatingSessionPartiallyByCampaignNoMoneyOnTheBalance() {
        RatingRequest ratingRequest = ratingRequest().but().withUnits(135).build();
        RatingRequest remainingRequest = ratingRequest().but().withUnits(75L).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(remainingRequest)
                .withGrantedUnits(60L)
                .build());

        when(balanceService.getRemainingBalanceExclVat(1)).thenReturn(2d);
        when(ratingService.estimate(2d, remainingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS)
                .withGrantedUnits(0L)
                .withRemainingBalance(2d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 60 units", response.getGrantedUnits(), equalTo(60L));
        assertThat("status is PARTIALLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.PARTIALLY_GRANTED));

        verify(ratingSessionDao).createSession(ratingSession().but().withReservedUnits(60L).build());
        verify(balanceService, never()).reserveAmountExclVat(anyInt(), anyDouble(), anyDouble());
    }
    @Test
    public void testStartSessionInsufficientFunds() {
        RatingRequest ratingRequest = ratingRequest().but().withUnits(135).build();

        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(ratingRequest)
                .build());

        when(balanceService.getRemainingBalanceExclVat(1)).thenReturn(2d);
        when(ratingService.estimate(2d, ratingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS)
                .withGrantedUnits(0L)
                .withRemainingBalance(2d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.startRatingSession(ratingRequest);
        assertThat("granted 0 units", response.getGrantedUnits(), equalTo(0L));
        assertThat("status is INSUFFICIENT_FUNDS", response.getStatus(), equalTo(PrepaidRatingStatus.INSUFFICIENT_FUNDS));

        verify(ratingSessionDao, never()).createSession(any(RatingSession.class));
        verify(balanceService, never()).reserveAmountExclVat(anyInt(), anyDouble(), anyDouble());
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
        when(balanceService.getRemainingBalanceExclVat(1)).thenReturn(100d);
        when(subscriptionCampaignService.estimate(ratingRequest)).thenReturn(aRatingResponse()
                .withRatingRequest(ratingRequest)
                .withGrantedUnits(0L)
                .build());

        when(ratingService.estimate(100d, ratingRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                .withGrantedUnits(96)
                .withRemainingBalance(99.02d)
                .build());

        PrepaidRatingResponse response = prepaidRatingService.updateRatingSession(61L, ratingRequest().but().withUnits(35L).build());
        assertThat("granted 35 units", response.getGrantedUnits(), equalTo(35L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));

        verify(ratingService).estimate(100d, ratingRequest);
        verify(ratingSessionDao).updateSession(ratingSession().but().withUsedUnits(61L).withReservedUnits(35L).withPrice(0.98).build());
        verify(balanceService).reserveAmountExclVat(1, 0.98d, 0.98d);
    }
    @Test
    public void testUpdateRatingSessionGrantedByStandardRatingPartlyUsed() {
        when(ratingSessionDao.findSession("123")).thenReturn(ratingSession().build());

        RatingRequest updatedRequest = ratingRequest().but().withUnits(96).build();
        when(subscriptionCampaignService.estimate(updatedRequest))
                .thenReturn(aRatingResponse().withRatingRequest(updatedRequest).build());

        when(balanceService.getRemainingBalanceExclVat(1)).thenReturn(100d);
        when(ratingService.estimate(100d, updatedRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                .withGrantedUnits(96)
                .withRemainingBalance(99.02d)
                .build());
        PrepaidRatingResponse response = prepaidRatingService.updateRatingSession(45L, ratingRequest().but().withUnits(35).build());
        assertThat("granted 35 units", response.getGrantedUnits(), equalTo(35L));
        assertThat("status is FULLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.FULLY_GRANTED));
        verify(ratingSessionDao).updateSession(ratingSession().but().withUsedUnits(45L).withReservedUnits(51L).withPrice(0.98d).build());
        verify(subscriptionCampaignService).estimate(updatedRequest);
    }
    @Test
    public void testUpdateRatingSessionPartlyGranted() {
        when(ratingSessionDao.findSession("123")).thenReturn(ratingSession().but().withPrice(0.98d).build());

        RatingRequest updatedRequest = ratingRequest().but().withUnits(196).build();
        when(subscriptionCampaignService.estimate(updatedRequest))
                .thenReturn(aRatingResponse().withRatingRequest(updatedRequest).build());

        when(balanceService.getRemainingBalanceExclVat(1)).thenReturn(1.5d);
        when(ratingService.estimate(1.5d, updatedRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.PARTIALLY_GRANTED)
                .withGrantedUnits(180)
                .withRemainingBalance(0.03d)
                .build());
        PrepaidRatingResponse response = prepaidRatingService.updateRatingSession(45L, ratingRequest().but().withUnits(135).build());
        assertThat("granted 119 units", response.getGrantedUnits(), equalTo(119L));
        assertThat("status is PARTIALLY_GRANTED", response.getStatus(), equalTo(PrepaidRatingStatus.PARTIALLY_GRANTED));
        verify(ratingSessionDao).updateSession(ratingSession().but().withUsedUnits(45L).withReservedUnits(135L).withPrice(1.47d).build());
        verify(subscriptionCampaignService).estimate(updatedRequest);
        verify(balanceService).reserveAmountExclVat(1, 0.98d, 1.47d);
    }
    @Test
    public void testUpdateRatingSessionInsufficientFunds() {
        when(ratingSessionDao.findSession("123")).thenReturn(ratingSession().but().withReservedUnits(120L).build());

        RatingRequest updatedRequest = ratingRequest().but().withUnits(180).build();
        when(subscriptionCampaignService.estimate(updatedRequest))
                .thenReturn(aRatingResponse().withRatingRequest(updatedRequest).build());

        when(balanceService.getRemainingBalanceExclVat(1)).thenReturn(1.2d);

        when(ratingService.estimate(1.2d, updatedRequest)).thenReturn(aPrepaidRatingResponse()
                .withStatus(PrepaidRatingStatus.PARTIALLY_GRANTED)
                .withGrantedUnits(120)
                .withRemainingBalance(0.22d)
                .build());
        PrepaidRatingResponse response = prepaidRatingService.updateRatingSession(120L, ratingRequest().but().withUnits(60).build());
        assertThat("granted 0 units", response.getGrantedUnits(), equalTo(0L));
        assertThat("status is INSUFFICIENT_FUNDS", response.getStatus(), equalTo(PrepaidRatingStatus.INSUFFICIENT_FUNDS));
        verify(ratingSessionDao).updateSession(ratingSession().but().withUsedUnits(120L).withReservedUnits(0L).withPrice(0.98d).build());
        verify(subscriptionCampaignService).estimate(updatedRequest);
    }
}