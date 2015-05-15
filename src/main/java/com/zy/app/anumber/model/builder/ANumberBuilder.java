package com.zy.app.anumber.model.builder;

import com.zy.app.anumber.model.ANumber;

import java.time.LocalDateTime;

/**
 * aba
 * 21/03/15
 */
public class ANumberBuilder {
    int number;ANumber.Status status;ANumber.Type type;LocalDateTime reservedUntil;
    Integer assignedToServiceId;
    LocalDateTime created;
    String reservationId;

    private ANumberBuilder() {
    }

    public static ANumberBuilder anANumber() {
        return new ANumberBuilder();
    }

    public ANumberBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public ANumberBuilder withStatus(ANumber.Status status) {
        this.status = status;
        return this;
    }

    public ANumberBuilder withType(ANumber.Type type) {
        this.type = type;
        return this;
    }

    public ANumberBuilder withReservedUntil(LocalDateTime reservedUntil) {
        this.reservedUntil = reservedUntil;
        return this;
    }

    public ANumberBuilder withAssignedToServiceId(Integer assignedToServiceId) {
        this.assignedToServiceId = assignedToServiceId;
        return this;
    }

    public ANumberBuilder withCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public ANumberBuilder withReservationId(String reservationId) {
        this.reservationId = reservationId;
        return this;
    }

    public ANumberBuilder but() {
        return anANumber().withNumber(number).withStatus(status).withType(type).withReservedUntil(reservedUntil).withAssignedToServiceId(assignedToServiceId).withCreated(created).withReservationId(reservationId);
    }

    public ANumber build() {
        ANumber aNumber = new ANumber();
        aNumber.setNumber(number);
        aNumber.setStatus(status);
        aNumber.setType(type);
        aNumber.setReservedUntil(reservedUntil);
        aNumber.setAssignedToServiceId(assignedToServiceId);
        aNumber.setCreated(created);
        aNumber.setReservationId(reservationId);
        return aNumber;
    }
}
