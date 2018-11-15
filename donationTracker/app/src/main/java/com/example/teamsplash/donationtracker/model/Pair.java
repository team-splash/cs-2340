package com.example.teamsplash.donationtracker.model;

class Pair {
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
