package com.zy.app.crm.main;

import com.zy.app.anumber.dao.ANumberDao;
import com.zy.app.anumber.model.ANumber;
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
import com.zy.app.fee.model.buillder.RunningFeeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.zy.app.crm.model.builder.ServiceBuilder.aService;
import static com.zy.app.crm.model.builder.SubscriptionBuilder.aSubscription;
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

    private ANumber getANumber(AccountSignup accountSignup) {
        ANumber aNumber;
        if (isEmpty(accountSignup.getReservationKey())) {
            List<Integer> aNumbers = aNumberDao.getOpenNumbers(1, ANumber.Type.NORMAL);
            if (aNumbers.size()==0) {
                throw new RuntimeException("no a-numbers available");
            }
            aNumber = aNumberDao.getANumber(aNumbers.get(0));
        } else {
            aNumber = aNumberDao.getReservedANumber(accountSignup.getReservationKey());
            if (aNumber==null || aNumber.getStatus()== ANumber.Status.ACTIVE) {
                throw new RuntimeException("reserved number not found");
            }
        }
        return aNumber;
    }

    private ANumber updateANumberToActive(ANumber aNumber, int serviceId) {
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

        Subscription subscription = aSubscription()
                    .withUserId(userId)
                    .withPricePlanCode(signupPackage.getPricePlanCode())
                    .withStatus(Subscription.Status.INITIAL)
                    .withStartDate(utilService.getCurrentDateTime())
                .build();

        int subscriptionId = subscriptionDao.createSubscription(subscription);

        ANumber aNumber = getANumber(accountSignup);

        Service service = aService()
                    .withPhoneNumber(aNumber.getNumber())
                    .withStatus(Service.Status.INITIAL)
                    .withSubscriptionId(subscriptionId)
                .build();

        int serviceId = serviceDao.createService(service);
        aNumberDao.updateANumber(updateANumberToActive(aNumber, serviceId));

        for (String feeCode : signupPackage.getFees()) {
            RunningFee runningFee = new RunningFeeBuilder()
                    .withFeeCode(feeCode)
                    .withSubscriptionId(subscriptionId)
                    .withNextChargeDate(utilService.getCurrentDate())
                    .withStatus(RunningFee.Status.ACTIVE)
                    .build();
            runningFeeDao.createRunningFee(runningFee);
        }

    }

}
