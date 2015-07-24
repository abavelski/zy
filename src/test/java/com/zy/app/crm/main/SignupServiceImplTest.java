package com.zy.app.crm.main;

import com.zy.app.anumber.dao.ANumberDao;
import com.zy.app.anumber.model.ANumber;
import com.zy.app.campaign.main.SubscriptionCampaignService;
import com.zy.app.common.main.UtilService;
import com.zy.app.crm.dao.ServiceDao;
import com.zy.app.crm.dao.SignupPackageDao;
import com.zy.app.crm.dao.SubscriptionDao;
import com.zy.app.crm.dao.UserDao;
import com.zy.app.crm.model.Service;
import com.zy.app.fee.dao.RunningFeeDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static com.zy.app.anumber.model.builder.ANumberBuilder.anANumber;
import static com.zy.app.crm.main.SignupTestData.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignupServiceImplTest {

    @Mock
    SubscriptionDao subscriptionDao;
    @Mock
    UserDao userDao;
    @Mock
    SignupPackageDao signupPackageDao;
    @Mock
    RunningFeeDao runningFeeDao;
    @Mock
    UtilService utilService;
    @Mock
    ServiceDao serviceDao;
    @Mock
    ANumberDao aNumberDao;
    @Mock
    SubscriptionCampaignService subscriptionCampaignService;

    @InjectMocks
    SignupServiceImpl signupService;

    LocalDateTime NOW = SignupTestData.now();
    LocalDate TODAY = SignupTestData.today();

    @Before
    public void setUp() throws Exception {
        //Given
        when(utilService.getCurrentDateTime()).thenReturn(NOW);
        when(utilService.getCurrentDate()).thenReturn(TODAY);
        when(userDao.createUser(SignupTestData.newUser())).thenReturn(1);
        when(signupPackageDao.findPackageByCode("pack1")).thenReturn(SignupTestData.newSignupPackage());
        when(subscriptionDao.createSubscription(SignupTestData.newSubscription(NOW))).thenReturn(2);
        when(utilService.getRandomKey()).thenReturn("reservation-id");
    }

    @Test
    public void testCreateAccount() {
        when(aNumberDao.getOpenNumbers(1, ANumber.Type.NORMAL)).thenReturn(Arrays.asList(123));
        when(aNumberDao.getANumber(123)).thenReturn(openANumber(NOW));
        //verify
        signupService.createAccount(newAccountSignupObject());

        verify(subscriptionDao).createSubscription(SignupTestData.newSubscription(NOW));
        verify(runningFeeDao).createRunningFee(SignupTestData.newRunningFee(TODAY));
    }

    @Test(expected = RuntimeException.class)
    public void testNoANumbersAvailable() {
        when(aNumberDao.getOpenNumbers(1, ANumber.Type.NORMAL)).thenReturn(new ArrayList<>());
        signupService.createAccount(newAccountSignupObject());
    }

    @Test
    public void testServiceCreated() {
        when(aNumberDao.getOpenNumbers(1, ANumber.Type.NORMAL)).thenReturn(Arrays.asList(123));
        when(aNumberDao.getANumber(123)).thenReturn(openANumber(NOW));
        signupService.createAccount(newAccountSignupObject());

        verify(serviceDao).createService(savedService());
    }

    @Test(expected = RuntimeException.class)
    public void testReservedNumberNotFound() {
        signupService.createAccount(accountSignupWithReservation());
    }

    @Test(expected = RuntimeException.class)
    public void testReservedNumberAlreadyActive() {
        when(aNumberDao.getReservedANumber("reservation-1")).thenReturn(reservedANumberButActive(NOW));
        signupService.createAccount(accountSignupWithReservation());

        verify(serviceDao).createService(savedService());
    }

    @Test
    public void testServiceWithReservedNumberCreated() {
        when(aNumberDao.getReservedANumber("reservation-1")).thenReturn(reservedANumber(NOW));
        signupService.createAccount(accountSignupWithReservation());

        verify(serviceDao).createService(savedService());
    }

    @Test
    public void testANumberIsUpdatedToActive() {
        when(aNumberDao.getReservedANumber("reservation-1")).thenReturn(reservedANumber(NOW));
        when(serviceDao.createService(any(Service.class))).thenReturn(4);
        signupService.createAccount(accountSignupWithReservation());
        verify(aNumberDao).updateANumber(updatedANumber());
    }
}