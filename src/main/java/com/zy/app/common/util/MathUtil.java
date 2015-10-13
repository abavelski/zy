package com.zy.app.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * alexei.bavelski@gmail.com
 * 27/09/15
 */
public class MathUtil {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
