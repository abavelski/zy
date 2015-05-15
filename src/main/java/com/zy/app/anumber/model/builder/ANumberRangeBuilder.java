package com.zy.app.anumber.model.builder;

import com.zy.app.anumber.model.ANumberRange;

/**
 * aba
 * 15/03/15
 */
public class ANumberRangeBuilder {
    int firstNumber;
    int lastNumber;

    private ANumberRangeBuilder() {
    }

    public static ANumberRangeBuilder anANumberRange() {
        return new ANumberRangeBuilder();
    }

    public ANumberRangeBuilder withFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
        return this;
    }

    public ANumberRangeBuilder withLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
        return this;
    }

    public ANumberRangeBuilder but() {
        return anANumberRange().withFirstNumber(firstNumber).withLastNumber(lastNumber);
    }

    public ANumberRange build() {
        ANumberRange aNumberRange = new ANumberRange();
        aNumberRange.setFirstNumber(firstNumber);
        aNumberRange.setLastNumber(lastNumber);
        return aNumberRange;
    }
}
