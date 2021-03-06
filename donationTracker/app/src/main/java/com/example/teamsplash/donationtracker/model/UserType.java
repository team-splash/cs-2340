package com.example.teamsplash.donationtracker.model;

/**
 * the enum for the usesr type. Several chosen types for user
 */
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

    /**
     * @return string version of the usertypes to be displayed, enums
     */
    @Override
    public String toString() {
        return representation;
    }
}