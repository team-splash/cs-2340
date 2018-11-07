package com.example.teamsplash.donationtracker.model;

public enum ItemType {
    NOT ("Select a category"),
    CLO ("Clothing"),
    HAT ("Hat"),
    KIT ("Kitchen"),
    ELE ("Electronics"),
    HSH ("Household"),
    OTH ("Other");

    private final String type;

    ItemType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }


    public static ItemType fromString(String text) {
        for (ItemType itemType : ItemType.values()) {
            if (itemType.type.equalsIgnoreCase(text)) {
                return itemType;
            }
        }
        return null;
    }

}