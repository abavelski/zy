package com.zy.app.rating.model;

import java.util.List;

public class TimePlan {
    private String code;
    private Integer startHour;
    private Integer endHour;
    private List<Charge> charges;

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePlan timePlan = (TimePlan) o;

        if (charges != null ? !charges.equals(timePlan.charges) : timePlan.charges != null) return false;
        if (code != null ? !code.equals(timePlan.code) : timePlan.code != null) return false;
        if (endHour != null ? !endHour.equals(timePlan.endHour) : timePlan.endHour != null) return false;
        if (startHour != null ? !startHour.equals(timePlan.startHour) : timePlan.startHour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (startHour != null ? startHour.hashCode() : 0);
        result = 31 * result + (endHour != null ? endHour.hashCode() : 0);
        result = 31 * result + (charges != null ? charges.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimePlan{" +
                "code='" + code + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", charges=" + charges +
                '}';
    }
}