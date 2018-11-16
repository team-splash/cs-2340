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

// --Commented out by Inspection START (11/15/18, 9:10 PM):
//    public String getRepresentation() {
//        return representation;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:10 PM)

    @Override
    public String toString() {
        return representation;
    }
}