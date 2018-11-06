package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.util.HashMap;

public final class Users {
    private static class Pair {
        private User user;
        private String userPassword;

        Pair(final User user, final String userPassword) {
            this.user = user;
            this.userPassword = userPassword;
        }

        @Override
        public boolean equals(Object anObject) {
            if (!(anObject instanceof Pair))
                return false;

            final Pair anotherPair = (Pair) anObject;

            if (user == null) {
                if (anotherPair.user != null)
                    return false;
            } else {
                if (!(user.equals(anotherPair.user)))
                    return false;
            }

            if (userPassword == null) {
                if (anotherPair.userPassword != null)
                    return false;
            } else {
                if (!(userPassword.equals(anotherPair.userPassword)))
                    return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + (user == null ? 0 : user.hashCode());
            result = 31 * result + (userPassword == null ? 0 : userPassword.hashCode());
            return result;
        }

        public User getUser() {
            return user;
        }

        public void setUser(final User user) {
            this.user = user;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(final String userPassword) {
            this.userPassword = userPassword;
        }
    }

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
