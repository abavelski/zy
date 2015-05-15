package com.zy.app.anumber.model;

import java.time.LocalDateTime;

public class ANumber {

    public enum Status {OPEN, ACTIVE, CLOSED}
    public enum Type {NORMAL, GOLD}

    int number;
    Status status;
    Type type;
    LocalDateTime reservedUntil;
    Integer assignedToServiceId;
    LocalDateTime created;
    String reservationId;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(LocalDateTime reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    public Integer getAssignedToServiceId() {
        return assignedToServiceId;
    }

    public void setAssignedToServiceId(Integer assignedToServiceId) {
        this.assignedToServiceId = assignedToServiceId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ANumber aNumber = (ANumber) o;

        if (number != aNumber.number) return false;
        if (assignedToServiceId != null ? !assignedToServiceId.equals(aNumber.assignedToServiceId) : aNumber.assignedToServiceId != null)
            return false;
        if (created != null ? !created.equals(aNumber.created) : aNumber.created != null) return false;
        if (reservationId != null ? !reservationId.equals(aNumber.reservationId) : aNumber.reservationId != null)
            return false;
        if (reservedUntil != null ? !reservedUntil.equals(aNumber.reservedUntil) : aNumber.reservedUntil != null)
            return false;
        if (status != aNumber.status) return false;
        if (type != aNumber.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (reservedUntil != null ? reservedUntil.hashCode() : 0);
        result = 31 * result + (assignedToServiceId != null ? assignedToServiceId.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (reservationId != null ? reservationId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ANumber{" +
                "number=" + number +
                ", status=" + status +
                ", type=" + type +
                ", reservedUntil=" + reservedUntil +
                ", assignedToServiceId=" + assignedToServiceId +
                ", created=" + created +
                ", reservationId='" + reservationId + '\'' +
                '}';
    }
}
