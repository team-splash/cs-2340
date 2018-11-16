package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;

public class Item implements Serializable {
    private final String time;
    private final Location location;
    private final String name;
    private final String desc;
    private final double value;
    private final ItemType itemtype;

    public Item(String time, Location location, String name, String desc, double value, ItemType itemType) {
        this.time = time;
        this.location = location;
        String s1 = name.substring(0, 1).toUpperCase();
        name = s1 + name.substring(1);
        this.name = name;
        this.desc = desc;
        this.value = value;
        this.itemtype = itemType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o instanceof User) if (((Item) o).getTime().equals(this.time))
            if (((Item) o).getLocation().equals(this.location))
                if (((Item) o).getDesc().equals(this.name))
                    if (((Item) o).getLongDesc().equals(this.desc))
                        if (((Item) o).getValue() == this.value)
                            return ((Item) o).getItemType().equals(this.itemtype);
        return false;

    }

    // Getter and Setter for time stamp
    public String getTime() {
        return time;
    }
// --Commented out by Inspection START (11/15/18, 9:13 PM):
//    private void setTime(String ts) {
//        this.time = ts;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:13 PM)

    // Getter and setter for location
    public Location getLocation() {
        return location;
    }


// --Commented out by Inspection START (11/15/18, 9:13 PM):
//    private void setLocation(Location loc) {
//        this.location = loc;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:13 PM)

    // Getter and Setter for description
    public String getDesc() {
        return name;
    }
// --Commented out by Inspection START (11/15/18, 9:13 PM):
//    public void setDesc(String description) {
//        this.name = description;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:13 PM)

    // Getter and Setter for long description
    public String getLongDesc() {
        return desc;
    }
// --Commented out by Inspection START (11/15/18, 9:13 PM):
//    public void setLongDesc(String longDescription) {
//        this.desc = longDescription;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:13 PM)

    // Getter and Setter for value
    public double getValue() {
        return value;
    }
// --Commented out by Inspection START (11/15/18, 9:13 PM):
//    public void setValue(double val) {
//        this.value = val;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:13 PM)

    // Getter and Setter for item type
    public ItemType getItemType() {
        return itemtype;
    }
// --Commented out by Inspection START (11/15/18, 9:13 PM):
//    public void setItemType(ItemType type) {
//        this.itemtype = type;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:13 PM)

    /**
     * SaveToText file method for items. It's about the same
     * as the User method, but I wanted to rewrite it by hand just to make sure I was aware of what was going on.
     * @param writer - a PrintWriter that is actually a buffer for another file.
     */
    public void saveAsText(PrintWriter writer) {
        String fullRep = this.getFullRep();
        Log.d("Full Rep of item to write: " +  fullRep, "LINE 96, SAVE AS TEXT: ITEM.JAVA");
        writer.write(fullRep);
        String newLine = String.format("%n");
        writer.write(newLine);
    }

    /**
     * Getting item data out of a String representation. Used prominently in loadAsText method.
     * @param line - line that represents an Item that is currently a string.
     * @return Item that is actually an item.
     */
    public static Item parseEntry(String line) {
        assert line != null;
        System.out.println("THIS IS THE LINE: " + line);
        String[] tokens = line.split(",");
        System.out.println(Arrays.toString(tokens));
        { if( !(tokens.length == 15) ) throw new AssertionError( "Failure message" ); }
        String actualItemType = tokens[14].substring(0, tokens[14].length() -1); // getting ItemTYPE.
        ItemType itemType = actualItemType.equals("Clothing") ? ItemType.CLO
                : actualItemType.equals("Hat") ? ItemType.HAT
                : actualItemType.equals("Kitchen") ? ItemType.KIT
                : actualItemType.equals("Electronic") ? ItemType.ELE
                : actualItemType.equals("Household") ? ItemType.HSH
                : ItemType.OTH;
        String convertLocation = tokens[1] + "," + tokens[2] + "," + tokens[3] + ","
                + tokens[4] + "," + tokens[5] + "," + tokens[6] + "," + tokens[7] + "," + tokens[8] + "," + tokens[9] + "," + tokens[10];
        Location convertedLoc = Location.parseEntry(convertLocation); // getting Location out of convoluted String data.
        return new Item(tokens[0], convertedLoc, tokens[11], tokens[12], Double.parseDouble(tokens[13]), itemType);
    }

    @Override
    public String toString() {
        return ("Time: " + time + " , Location: " + location + " , Name: " + name +
                " , Description: " + desc + " , Price: " + value + " , Category: " + itemtype);
    }

    /**
     * The fullrep model that allows us to get an Item via a String representation that's easy to read/manipulate.
     * @return String that is the fullRep string.
     */
    private String getFullRep() {
        return (time + "," + location.getFullRep()+ "," + name + "," + desc + "," + value + "," + itemtype);
    }
}
