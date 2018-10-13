package com.example.teamsplash.donationtracker.model;

public class Model {
    public static boolean validateEmailAddress(final String emailAddress) {
        return Login.validateEmailAddress(emailAddress);
    }

    public static boolean validatePassword(final String password) {
        return Login.validatePassword(password);
    }

    public static void addUser(final String userFirstName, final String userLastName, final String userEmailAddress,
                               final UserType userType, final String userPassword) throws Users.UserEmailAddressAlreadyRegistered {
        Users.getInstance().add(userFirstName, userLastName, userEmailAddress, userType, userPassword);
    }

    public static boolean checkUserPassword(final String userEmailAddress, final String userPassword)
            throws Users.UserEmailAddressNotRegistered {
        return Users.getInstance().checkPassword(userEmailAddress, userPassword);
    }
}
