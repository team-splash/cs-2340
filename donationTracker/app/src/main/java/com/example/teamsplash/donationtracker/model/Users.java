package com.example.teamsplash.donationtracker.model;

import java.util.HashMap;

public class Users {
    public class UserEmailAddressAlreadyRegistered extends Exception {
    }

    public class UserEmailAddressNotRegistered extends Exception {
    }

    private static final Users instance = new Users();

    public static Users getInstance() {
        return instance;
    }

    private static HashMap<String, User> users;

    private Users() {
        users = new HashMap<String, User>();
    }

    /**
     * Register a user only if the user's email address is not already registered.
     */
    public void add(final User user) throws UserEmailAddressAlreadyRegistered {
        final String userEmailAddress = user.getEmail();
        final User previousUser = users.put(userEmailAddress, user);

        if (previousUser != null) {
            users.put(userEmailAddress, previousUser);
            throw new UserEmailAddressAlreadyRegistered();
        }
    }

    public User getByEmailAddress(final String userEmailAddress) throws UserEmailAddressNotRegistered {
        final User user = users.get(userEmailAddress);

        if (user == null)
            throw new UserEmailAddressNotRegistered();

        return user;
    }
}
