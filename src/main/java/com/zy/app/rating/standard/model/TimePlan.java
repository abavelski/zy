package com.zy.app.rating.standard.model;

import java.util.List;

public class TimePlan {
    private String code;
    private Integer startHour;
    private Integer endHour;
    private List<Charge> charges;
    private List<String> campaigns;

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

    public List<String> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<String> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePlan timePlan = (TimePlan) o;

        if (code != null ? !code.equals(timePlan.code) : timePlan.code != null) return false;
        if (startHour != null ? !startHour.equals(timePlan.startHour) : timePlan.startHour != null) return false;
        if (endHour != null ? !endHour.equals(timePlan.endHour) : timePlan.endHour != null) return false;
        if (charges != null ? !charges.equals(timePlan.charges) : timePlan.charges != null) return false;
        return !(campaigns != null ? !campaigns.equals(timePlan.campaigns) : timePlan.campaigns != null);

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (startHour != null ? startHour.hashCode() : 0);
        result = 31 * result + (endHour != null ? endHour.hashCode() : 0);
        result = 31 * result + (charges != null ? charges.hashCode() : 0);
        result = 31 * result + (campaigns != null ? campaigns.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimePlan{" +
                "code='" + code + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", charges=" + charges +
                ", campaigns=" + campaigns +
                '}';
    }
}