package com.zy.app.crm.main;

import com.zy.app.anumber.model.ANumber;
import com.zy.app.crm.model.*;
import com.zy.app.fee.model.RunningFee;
import com.zy.app.fee.model.buillder.RunningFeeBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.zy.app.anumber.model.builder.ANumberBuilder.anANumber;
import static com.zy.app.crm.model.builder.AccountSignupBuilder.anAccountSignup;
import static com.zy.app.crm.model.builder.ServiceBuilder.aService;
import static com.zy.app.crm.model.builder.SignupPackageBuilder.aSignupPackage;
import static com.zy.app.crm.model.builder.SubscriptionBuilder.aSubscription;
import static com.zy.app.crm.model.builder.UserBuilder.anUser;


public class SignupTestData {

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static User newUser() {
        return anUser()
                .withFirstName("Vladimir")
                .withLastName("Putin")
                .withAddress("Red Square 1")
                .withCity("Moscow")
                .withZip("10000")
                .build();
    }

    public static AccountSignup newAccountSignupObject() {
        return anAccountSignup()
                .withUser(newUser())
                .withPackageCode("pack1")
                .build();
    }

    public static Service savedService() {
        return aService()
                .withPhoneNumber(123)
                .withStatus(Service.Status.INITIAL)
                .withSubscriptionId(2)
                .build();
    }

    public static AccountSignup accountSignupWithReservation() {
        return anAccountSignup()
                .withUser(newUser())
                .withPackageCode("pack1")
                .withReservationKey("reservation-1")
                .build();
    }

    public static SignupPackage newSignupPackage() {
        return aSignupPackage()
                .withCode("pack1")
                .withPricePlanCode("pp1")
                .withFees(Arrays.asList("fee1"))
                .build();
    }

    public static Subscription newSubscription(LocalDateTime now) {
        return aSubscription()
                .withPricePlanCode("pp1")
                .withStartDate(now)
                .withStatus(Subscription.Status.INITIAL)
                .withUserId(1)
                .build();
    }

    public static RunningFee newRunningFee(LocalDate today) {
        return new RunningFeeBuilder()
                .withFeeCode("fee1")
                .withNextChargeDate(today)
                .withStatus(RunningFee.Status.ACTIVE)
                .withSubscriptionId(2)
                .build();
    }

    public static ANumber reservedANumber(LocalDateTime now) {
        return anANumber()
                .withNumber(123)
                .withReservationId("reservation-1")
                .withReservedUntil(now.plusMinutes(10))
                .withStatus(ANumber.Status.OPEN)
                .build();
    }

    public static ANumber openANumber(LocalDateTime now) {
        return anANumber()
                .withNumber(123)
                .withStatus(ANumber.Status.OPEN)
                .build();
    }

    public static ANumber updatedANumber() {
        return anANumber()
                .withNumber(123)
                .withStatus(ANumber.Status.ACTIVE)
                .withAssignedToServiceId(4)
                .build();
    }

    public static ANumber reservedANumberButActive(LocalDateTime now) {
        return anANumber()
                .withNumber(123)
                .withReservationId("reservation-1")
                .withReservedUntil(now.plusMinutes(10))
                .withStatus(ANumber.Status.ACTIVE)
                .build();
    }

}
