package com.example.teamsplash.donationtracker.model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Login {
    private static final long MINIMUM_PASSWORD_LENGTH = 8;

    public static boolean validateEmailAddress(final String emailAddressRepresentation) {
        try {
            final InternetAddress emailAddress = new InternetAddress(emailAddressRepresentation, true);
            return true;
        } catch (AddressException exception) {
            return false;
        }
    }

    public static boolean validatePassword(final String passwordRepresentation) {
        return passwordRepresentation.length() >= MINIMUM_PASSWORD_LENGTH;
    }
}
