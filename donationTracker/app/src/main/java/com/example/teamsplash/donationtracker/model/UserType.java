package com.example.teamsplash.donationtracker.model;

public enum UserType {
    USER("User"),
    LOCATION_EMPLOYEE("Location Employee"),
    MANAGER("Manager"),
    ADMINISTRATOR("Admin");

    private final String representation;

    UserType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}