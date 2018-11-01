package com.example.teamsplash.donationtracker.model;

import java.io.Serializable;

public class Item implements Serializable {
    private String time;
    private Location location;
    private String name;
    private String desc;
    private double value;
    private ItemType itemtype;

    public Item(String time, Location location, String name, String desc, double value, ItemType itemType) {
        this.time = time.toString();
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
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return (((Item) o).getTime().equals(this.time)
                && ((Item) o).getLocation().equals(this.location)
                && ((Item) o).getDesc().equals(this.name)
                && ((Item) o).getLongDesc().equals(this.desc)
                && ((Item) o).getValue() == this.value
                && ((Item) o).getItemType().equals(this.itemtype));

    }

    // Getter and Setter for time stamp
    public String getTime() {
        return time;
    }
    private void setTime(String ts) {
        this.time = ts.toString();
    }

    // Getter and setter for location
    public Location getLocation() {
        return location;
    }
    private void setLocation(Location loc) {
        this.location = loc;
    }

    // Getter and Setter for description
    public String getDesc() {
        return name;
    }
    public void setDesc(String description) {
        this.name = description;
    }

    // Getter and Setter for long description
    public String getLongDesc() {
        return desc;
    }
    public void setLongDesc(String longDescription) {
        this.desc = longDescription;
    }

    // Getter and Setter for value
    public double getValue() {
        return value;
    }
    public void setValue(double val) {
        this.value = val;
    }

    // Getter and Setter for item type
    public ItemType getItemType() {
        return itemtype;
    }
    public void setItemType(ItemType type) {
        this.itemtype = type;
    }

    @Override
    public String toString() {
        return ("Time: " + time + " , Location: " + location + " , Name: " + name +
                " , Description: " + desc + " , Price: " + value + " , Category: " + itemtype);
    }
}
