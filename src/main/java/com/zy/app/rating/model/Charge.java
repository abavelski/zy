package com.zy.app.rating.model;

import com.zy.app.rating.main.plugin.rating.RatingType;

public class Charge {
    private Double rate;
    private String code;
    private RatingType ratingPlugin;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public RatingType getRatingPlugin() {
        return ratingPlugin;
    }

    public void setRatingPlugin(RatingType ratingPlugin) {
        this.ratingPlugin = ratingPlugin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Charge charge = (Charge) o;

        if (code != null ? !code.equals(charge.code) : charge.code != null) return false;
        if (rate != null ? !rate.equals(charge.rate) : charge.rate != null) return false;
        if (ratingPlugin != charge.ratingPlugin) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rate != null ? rate.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (ratingPlugin != null ? ratingPlugin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "rate=" + rate +
                ", code='" + code + '\'' +
                ", ratingPlugin=" + ratingPlugin +
                '}';
    }
}