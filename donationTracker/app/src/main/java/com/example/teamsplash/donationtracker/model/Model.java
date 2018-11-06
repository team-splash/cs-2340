package com.example.teamsplash.donationtracker.model;

import java.util.NoSuchElementException;

public class Model {
    public static boolean validateEmailAddress(final String emailAddressRepresentation) {
        return Login.validateEmailAddress(emailAddressRepresentation);
    }

    public static boolean validatePassword(final String passwordRepresentation) {
        return Login.validatePassword(passwordRepresentation);
    }

    public static void addUser(final String userFirstName, final String userLastName, final String userEmailAddress,
                               final UserType userType, final String userPassword) throws Users.UserEmailAddressAlreadyRegistered {
        Users.getInstance().add(userFirstName, userLastName, userEmailAddress, userType, userPassword);
    }

    public static boolean checkUserPassword(final String userEmailAddress, final String userPassword) {
        final User user = Users.getInstance().get(userEmailAddress, userPassword);
        Session.getInstance().setUser(user);
        return user != null;
    }

    public static User getUser() throws NoSuchElementException {
        return Session.getInstance().getUser();
    }
}
