package com.zy.app.crm.model.builder;

import com.zy.app.crm.model.User;

/**
 * aba
 * 26/02/15
 */
public class UserBuilder {
    int id;
    String firstName;
    String lastName;
    String address;
    String city;
    String zip;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder withZip(String zip) {
        this.zip = zip;
        return this;
    }

    public UserBuilder but() {
        return anUser().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address).withCity(city).withZip(zip);
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setCity(city);
        user.setZip(zip);
        return user;
    }
}
