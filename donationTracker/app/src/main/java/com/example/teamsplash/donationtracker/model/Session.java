package com.example.teamsplash.donationtracker.model;

import java.util.NoSuchElementException;
import java.util.Optional;

final class Session {
    private static final Session instance = new Session();

    static Session getInstance() {
        return instance;
    }

    private User user = null;

    private Session() {
    }

    User getUser() throws NoSuchElementException {
        if (user == null)
            throw new NoSuchElementException();

        return user;
    }

    void setUser(final User user) {
        this.user = user;
    }
}
