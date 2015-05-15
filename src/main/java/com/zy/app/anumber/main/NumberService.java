package com.zy.app.anumber.main;

import com.zy.app.anumber.model.ANumber;
import com.zy.app.anumber.model.ANumberRange;

import java.util.List;


public interface NumberService {

    public void createNumberRange(ANumberRange range);
    public List<Integer> getRandomANumbers(int nr, ANumber.Type type);
    public String reserveANumber(Integer aNumber);
}
