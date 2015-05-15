package com.zy.app.fee.main;

import com.zy.app.fee.model.RunningFee;
import com.zy.app.common.model.ChargeLine;

public interface FeeService {
    ChargeLine chargeFee(RunningFee runningFee);
    int chargeAllFees();
}
