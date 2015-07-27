package com.zy.app.crm.main;

import com.zy.app.anumber.dao.ANumberDao;
import com.zy.app.anumber.model.ANumber;
import com.zy.app.rating.campaign.main.SubscriptionCampaignService;
import com.zy.app.rating.campaign.model.CampaignSignupRequest;
import com.zy.app.common.main.UtilService;
import com.zy.app.crm.dao.ServiceDao;
import com.zy.app.crm.dao.SignupPackageDao;
import com.zy.app.crm.dao.SubscriptionDao;
import com.zy.app.crm.dao.UserDao;
import com.zy.app.crm.model.AccountSignup;
import com.zy.app.crm.model.Service;
import com.zy.app.crm.model.SignupPackage;
import com.zy.app.crm.model.Subscription;
import com.zy.app.fee.dao.RunningFeeDao;
import com.zy.app.fee.model.RunningFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import static com.zy.app.crm.model.builder.ServiceBuilder.aService;
import static com.zy.app.crm.model.builder.SubscriptionBuilder.aSubscription;
import static com.zy.app.fee.model.buillder.RunningFeeBuilder.aRunningFee;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class SignupServiceImpl implements SignupService {

    @Autowired
    SubscriptionDao subscriptionDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ServiceDao serviceDao;
    @Autowired
    ANumberDao aNumberDao;
    @Autowired
    SignupPackageDao signupPackageDao;
    @Autowired
    RunningFeeDao runningFeeDao;
    @Autowired
    UtilService utilService;
    @Autowired
    SubscriptionCampaignService subscriptionCampaignService;

    private ANumber getANumberByReservationKey(String reservationKey) {
        ANumber aNumber;
        if (isEmpty(reservationKey)) {
            List<Integer> aNumbers = aNumberDao.getOpenNumbers(1, ANumber.Type.NORMAL);
            if (aNumbers.size()==0) {
                throw new RuntimeException("no a-numbers available");
            }
            aNumber = aNumberDao.getANumber(aNumbers.get(0));
        } else {
            aNumber = aNumberDao.getReservedANumber(reservationKey);
            if (aNumber==null || aNumber.getStatus()== ANumber.Status.ACTIVE) {
                throw new RuntimeException("reserved number not found");
            }
        }
        return aNumber;
    }

    private ANumber setANumberActive(ANumber aNumber, int serviceId) {
        aNumber.setAssignedToServiceId(serviceId);
        aNumber.setStatus(ANumber.Status.ACTIVE);
        aNumber.setReservationId(null);
        aNumber.setReservedUntil(null);
        return aNumber;
    }

    @Override
    @Transactional
    public void createAccount(AccountSignup accountSignup) {
        int userId = userDao.createUser(accountSignup.getUser());
        SignupPackage signupPackage = signupPackageDao.findPackageByCode(accountSignup.getPackageCode());

        int subscriptionId = subscriptionDao.createSubscription(
                aSubscription()
                    .withUserId(userId)
                    .withPricePlanCode(signupPackage.getPricePlanCode())
                    .withStatus(Subscription.Status.INITIAL)
                    .withStartDate(utilService.getCurrentDateTime())
                .build());

        ANumber aNumber = getANumberByReservationKey(accountSignup.getReservationKey());

        int serviceId = serviceDao.createService(
                aService()
                    .withPhoneNumber(aNumber.getNumber())
                    .withStatus(Service.Status.INITIAL)
                    .withSubscriptionId(subscriptionId)
                .build());
        setANumberActive(aNumber, serviceId);
        aNumberDao.updateANumber(aNumber);

        for (String feeCode : signupPackage.getFees()) {
            runningFeeDao.createRunningFee(
                        aRunningFee()
                            .withFeeCode(feeCode)
                            .withSubscriptionId(subscriptionId)
                            .withNextChargeDate(utilService.getCurrentDate())
                            .withStatus(RunningFee.Status.ACTIVE)
                        .build());
        }

        List<CampaignSignupRequest> campaigns = signupPackage.getCampaigns();
        if (campaigns!=null) {
            for (CampaignSignupRequest campaignRequest : campaigns) {
                campaignRequest.setSubscriptionId(subscriptionId);
                subscriptionCampaignService.signupToCampaign(campaignRequest);
            }
        }
    }

    @Override
    public List<SignupPackage> getSortedSignupPackages() {
        List<SignupPackage> allSignupPackages = signupPackageDao.getAllSignupPackages();
        allSignupPackages.sort((o1, o2) -> o1.getPriority()-o2.getPriority());
        return allSignupPackages;
    }

}
