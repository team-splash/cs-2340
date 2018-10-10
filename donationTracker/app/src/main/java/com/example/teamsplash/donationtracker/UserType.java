package com.example.teamsplash.donationtracker;

public enum UserType {
    USER("User"),
    LOCATION_EMPLOYEE("Location Employee"),
    MANAGER("Manager"),
    ADMINISTRATOR("Admin");

    private String representation;

    UserType(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
