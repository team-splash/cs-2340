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

// --Commented out by Inspection START (11/15/18, 9:14 PM):
//    public String getType() {
//        return type;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:14 PM)

    public String toString() {
        return type;
    }


// --Commented out by Inspection START (11/15/18, 9:14 PM):
//    public static ItemType fromString(String text) {
//        for (ItemType itemType : ItemType.values()) {
//            if (itemType.type.equalsIgnoreCase(text)) {
//                return itemType;
//            }
//        }
//        return null;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:14 PM)

}