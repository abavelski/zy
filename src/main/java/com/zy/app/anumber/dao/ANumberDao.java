package com.zy.app.anumber.dao;

import com.zy.app.anumber.model.ANumber;

import java.util.List;

/**
 * aba
 * 15/03/15
 */
public interface ANumberDao {

    public void createANumber(ANumber aNumber);
    public void updateANumber(ANumber aNumber);
    public ANumber getANumber(Integer number);
    public ANumber getReservedANumber(String reservationId);

    public List<Integer> getOpenNumbers(Integer nr, ANumber.Type type);


}
