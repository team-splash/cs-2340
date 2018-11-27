package com.example.teamsplash.donationtracker.model;

/**
 * enums for locations types. There are specific types, so we make enums
 */
@SuppressWarnings("unused")
public enum LocationType {
    DR ("Drop Off"),
    ST ("Store"),
    WA ("Warehouse");

    private final String type;

    LocationType(String type) {
        this.type = type;
    }

    /**
     * @return type of the locations into strings
     */
    public String getType() {
        return type;
    }

    /**
     * @return the type into string form
     */
    public String toString() {
        return type;
    }


    /**
     * @param text the string of the location type
     * @return the location type based on the string parsed in
     */
    public static LocationType fromString(String text) {
        for (LocationType locationType : LocationType.values()) {
            if (locationType.type.equalsIgnoreCase(text)) {
                return locationType;
            }
        }
        return null;
    }

}