package com.zy.app.anumber.model.builder;

import com.zy.app.anumber.model.ReservationResponse;


public class ReservationResponseBuilder {
    String reservationKey;

    private ReservationResponseBuilder() {
    }

    public static ReservationResponseBuilder aReservationResponse() {
        return new ReservationResponseBuilder();
    }

    public ReservationResponseBuilder withReservationKey(String reservationKey) {
        this.reservationKey = reservationKey;
        return this;
    }


    public ReservationResponseBuilder but() {
        return aReservationResponse().withReservationKey(reservationKey);
    }

    public ReservationResponse build() {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setReservationKey(reservationKey);
        return reservationResponse;
    }
}
