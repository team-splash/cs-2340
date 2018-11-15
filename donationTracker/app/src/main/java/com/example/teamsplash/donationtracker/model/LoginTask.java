package com.example.teamsplash.donationtracker.model;

public interface LoginTask {
    void onSuccess(final User user);
    void onFailure(final Exception e);
}
