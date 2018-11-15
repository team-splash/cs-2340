package com.example.teamsplash.donationtracker.model;

import java.util.HashMap;

public final class Users {
    public class UserEmailAddressAlreadyRegistered extends Exception {
    }

    private static final Users instance = new Users();

    static Users getInstance() {
        return instance;
    }

    private HashMap<String, Pair> users;

    private Users() {
        users = new HashMap<>();
    }

    void add(final String userFirstName, final String userLastName, final String userEmailAddress, final UserType userType,
             final String userPassword) throws UserEmailAddressAlreadyRegistered {
        final Pair pair = new Pair(new User(userFirstName, userLastName, userEmailAddress, userType), userPassword);
        final Pair previousPair = users.put(userEmailAddress, pair);

        if (previousPair != null) {
            users.put(userEmailAddress, previousPair);
            throw new UserEmailAddressAlreadyRegistered();
        }
    }

    User get(final String userEmailAddress, final String userPassword) {
        final Pair pair = users.get(userEmailAddress);

        if (pair == null)
            return null;

        if (!(pair.getUserPassword().equals(userPassword)))
            return null;

        return pair.getUser();
    }
}
