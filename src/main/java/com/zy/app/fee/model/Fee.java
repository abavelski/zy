package com.zy.app.fee.model;


public class Fee {

    public enum Type { PRE, ONCE }
    public enum Period { NONE, WEEK, TWO_WEEKS, MONTH, YEAR }

    String code;
    String description;
    Type type;
    double amount;
    Period period;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fee fee = (Fee) o;

        if (Double.compare(fee.amount, amount) != 0) return false;
        if (code != null ? !code.equals(fee.code) : fee.code != null) return false;
        if (description != null ? !description.equals(fee.description) : fee.description != null) return false;
        if (type != fee.type) return false;
        return period == fee.period;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = code != null ? code.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (period != null ? period.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", period=" + period +
                '}';
    }
}
