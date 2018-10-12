package com.example.teamsplash.donationtracker.model;

public class Model {
    public static boolean validateEmailAddress(final String emailAddress) {
        return Login.validateEmailAddress(emailAddress);
    }

    public static boolean validatePassword(final String password) {
        return Login.validatePassword(password);
    }

    public static User getUserByEmailAddress(final String userEmailAddress) throws Users.UserEmailAddressNotRegistered {
        return Users.getInstance().getByEmailAddress(userEmailAddress);
    }
}
