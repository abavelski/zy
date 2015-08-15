package com.zy.app.anumber.main;

import com.zy.app.anumber.model.ANumber;
import com.zy.app.anumber.model.ANumberRange;

import java.util.List;


public interface NumberService {

    void createNumberRange(ANumberRange range);
    List<Integer> getRandomANumbers(int nr, ANumber.Type type);
    String reserveANumber(Integer aNumber);
}
