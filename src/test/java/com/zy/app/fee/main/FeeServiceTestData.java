package com.zy.app.fee.main;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.fee.model.Fee;
import com.zy.app.fee.model.buillder.FeeBuilder;
import com.zy.app.fee.model.RunningFee;
import com.zy.app.fee.model.buillder.RunningFeeBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static com.zy.app.common.model.ChargeLineBuilder.aChargeLine;


public class FeeServiceTestData {
    public static LocalDate FEEDATE = LocalDate.of(2015, Month.FEBRUARY, 5);
    public  static LocalDateTime CHARGEDATE = LocalDateTime.of(2015, Month.FEBRUARY, 5, 8, 0);
    public  static LocalDate NEXTCHARGEDATE = LocalDate.of(2015, Month.MARCH, 5);

    public static RunningFee savedRunningFee() {
        return new RunningFeeBuilder()
                .withId(1)
                .withFeeCode("fee1")
                .withNextChargeDate(FEEDATE)
                .withStatus(RunningFee.Status.ACTIVE)
                .withSubscriptionId(2)
                .build();
    }

    public static RunningFee terminatedRunningFeeOnce() {
        RunningFee onceRunningFee=savedRunningFee();
        onceRunningFee.setStatus(RunningFee.Status.TERMINATED);
        onceRunningFee.setNextChargeDate(null);
        return onceRunningFee;
    }

    public static RunningFee updatedRunningFee() {
        RunningFee runningFee = savedRunningFee();
        runningFee.setNextChargeDate(NEXTCHARGEDATE);
        return runningFee;
    }

    public static ChargeLine newFeeChargeLine() {
        return aChargeLine()
                .withSubscriptionId(2)
                .withChargeDate(CHARGEDATE)
                .withDescription("my fee1")
                .withTotal(50d)
                .build();
    }

    public static Fee prepaidFee() {
        return new FeeBuilder()
                .withDescription("my fee1")
                .withCode("fee1")
                .withPeriod(Fee.Period.MONTH)
                .withType(Fee.Type.PRE)
                .withAmount(50d)
                .build();
    }

    public static Fee onceFee() {
        Fee fee = prepaidFee();
        fee.setType(Fee.Type.ONCE);
        fee.setPeriod(Fee.Period.NONE);
        return fee;
    }



}
