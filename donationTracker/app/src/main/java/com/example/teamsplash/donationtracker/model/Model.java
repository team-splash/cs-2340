package com.example.teamsplash.donationtracker.model;

public class Model {
    public static boolean validateEmailAddress(final String emailAddressRepresentation) {
        return Login.validateEmailAddress(emailAddressRepresentation);
    }

    public static boolean validatePassword(final String passwordRepresentation) {
        return Login.validatePassword(passwordRepresentation);
    }
}
