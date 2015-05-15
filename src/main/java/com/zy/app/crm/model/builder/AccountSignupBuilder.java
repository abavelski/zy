package com.zy.app.crm.model.builder;

import com.zy.app.crm.model.AccountSignup;
import com.zy.app.crm.model.User;

/**
 * aba
 * 21/03/15
 */
public class AccountSignupBuilder {
    private String packageCode;
    private User user;
    private String reservationKey;

    private AccountSignupBuilder() {
    }

    public static AccountSignupBuilder anAccountSignup() {
        return new AccountSignupBuilder();
    }

    public AccountSignupBuilder withPackageCode(String packageCode) {
        this.packageCode = packageCode;
        return this;
    }

    public AccountSignupBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public AccountSignupBuilder withReservationKey(String reservationKey) {
        this.reservationKey = reservationKey;
        return this;
    }

    public AccountSignupBuilder but() {
        return anAccountSignup().withPackageCode(packageCode).withUser(user).withReservationKey(reservationKey);
    }

    public AccountSignup build() {
        AccountSignup accountSignup = new AccountSignup();
        accountSignup.setPackageCode(packageCode);
        accountSignup.setUser(user);
        accountSignup.setReservationKey(reservationKey);
        return accountSignup;
    }
}
