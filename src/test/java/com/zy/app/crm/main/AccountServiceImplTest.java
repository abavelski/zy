package com.zy.app.crm.main;

import com.zy.app.common.main.UtilService;
import com.zy.app.crm.dao.ServiceDao;
import com.zy.app.crm.dao.SubscriptionDao;
import com.zy.app.crm.dao.UserDao;
import com.zy.app.crm.model.Service;
import com.zy.app.crm.model.Subscription;
import com.zy.app.crm.model.builder.ServiceBuilder;
import com.zy.app.crm.model.builder.SubscriptionBuilder;
import com.zy.app.fee.dao.RunningFeeDao;
import com.zy.app.fee.model.RunningFee;
import com.zy.app.fee.model.buillder.RunningFeeBuilder;
import com.zy.app.rating.campaign.main.SubscriptionCampaignService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static com.zy.app.crm.model.builder.ServiceBuilder.aService;
import static com.zy.app.crm.model.builder.SubscriptionBuilder.aSubscription;
import static com.zy.app.fee.model.buillder.RunningFeeBuilder.aRunningFee;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * alexei.bavelski@gmail.com
 * 29/08/15
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;
    @Mock
    ServiceDao serviceDao;
    @Mock
    SubscriptionDao subscriptionDao;
    @Mock
    UserDao userDao;
    @Mock
    RunningFeeDao runningFeeDao;
    @Mock
    SubscriptionCampaignService subscriptionCampaignService;
    @Mock
    UtilService utilService;

    ServiceBuilder serviceBuilder = aService().withSubscriptionId(1);
    SubscriptionBuilder subscriptionBuilder = aSubscription().withId(1).withUserId(1);
    RunningFeeBuilder runningFeeBuilder = aRunningFee()
                    .withFeeCode("fee1")
                    .withStatus(RunningFee.Status.INITIAL)
                    .withSubscriptionId(1);
    final LocalDate today = SignupTestData.today();

    @Before
    public void setUp() throws Exception {
        when(serviceDao.findServiceByPhoneNumber(123)).thenReturn(serviceBuilder.build());
        when(subscriptionDao.getSubscriptionById(1)).thenReturn(subscriptionBuilder.build());
        when(runningFeeDao.getRunningFeesBySubscriptionId(1)).thenReturn(Arrays.asList(runningFeeBuilder.build()));

        when(utilService.getCurrentDate()).thenReturn(today);

    }

    @Test
    public void testActivateAccount() {
        accountService.activateAccount(123);

        verify(subscriptionCampaignService).activateCampaignsForSubscription(1);
        verify(serviceDao).updateService(serviceBuilder.but().withStatus(Service.Status.ACTIVE).build());
        verify(subscriptionDao).updateSubscription(subscriptionBuilder.but().withStatus(Subscription.Status.ACTIVE).build());
        verify(runningFeeDao).updateRunningFee(runningFeeBuilder.but()
                .withStatus(RunningFee.Status.ACTIVE)
                .withNextChargeDate(today)
                .build());
    }
}