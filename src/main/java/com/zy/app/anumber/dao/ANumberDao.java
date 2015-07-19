package com.zy.app.anumber.dao;

import com.zy.app.anumber.model.ANumber;

import java.util.List;

/**
 * aba
 * 15/03/15
 */
public interface ANumberDao {

    void createANumber(ANumber aNumber);
    void updateANumber(ANumber aNumber);
    ANumber getANumber(Integer number);
    ANumber getReservedANumber(String reservationId);

    List<Integer> getOpenNumbers(Integer nr, ANumber.Type type);


}
