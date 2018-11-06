package com.example.teamsplash.donationtracker.model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

class Login {
    private static final long MINIMUM_PASSWORD_LENGTH = 8;

    static boolean validateEmailAddress(final String emailAddressRepresentation) {
        try {
            final InternetAddress emailAddress = new InternetAddress(emailAddressRepresentation, true);
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    static boolean validatePassword(final String passwordRepresentation) {
        return passwordRepresentation.length() >= MINIMUM_PASSWORD_LENGTH;
    }
}
