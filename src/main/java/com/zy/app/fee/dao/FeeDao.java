package com.zy.app.fee.dao;

import com.zy.app.fee.model.Fee;

import java.util.List;

public interface FeeDao {

    Fee findFeeByCode(String code);
    List<Fee> getAllFees();

}
