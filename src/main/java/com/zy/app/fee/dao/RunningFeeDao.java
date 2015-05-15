package com.zy.app.fee.dao;

import com.zy.app.fee.model.RunningFee;

import java.util.List;

/**
 * Created by aba on 17/02/15.
 */
public interface RunningFeeDao {

    int createRunningFee(RunningFee fee);
    List<RunningFee> getReadyToChargeFees();
    void updateRunningFee(RunningFee fee);

}
