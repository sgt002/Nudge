package com.ch.nudge;

import java.io.Serializable;

public class User implements Serializable {

    public String email;
    public String phoneNumber;
    public String preference;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email) {
        this.email = email;
    }

    public User(String email, String phoneNumber, String preference) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.preference = preference;
    }

    public void addNumberPref(String phoneNumber, String preference) {
        this.phoneNumber = phoneNumber;
        this.preference = preference;
    }

}
