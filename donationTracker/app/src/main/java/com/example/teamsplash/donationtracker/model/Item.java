package com.example.teamsplash.donationtracker.model;

public class Item {
    private String timestamp;
    private Location location;
    private String desc;
    private String longDesc;
    private double value;
    private ItemType itemtype;

    public Item(Location location, String desc, String longDesc, double value, ItemType itemType) {
        Long tsLong = System.currentTimeMillis()/1000;
        this.timestamp = tsLong.toString();
        this.location = location;
        this.desc = desc;
        this.longDesc = longDesc;
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
        return (((Item) o).getTimestamp().equals(this.timestamp)
                && ((Item) o).getLocation().equals(this.location)
                && ((Item) o).getDesc().equals(this.desc)
                && ((Item) o).getLongDesc().equals(this.longDesc)
                && ((Item) o).getValue() == this.value
                && ((Item) o).getItemType().equals(this.itemtype));

    }

    // Getter and Setter for time stamp
    public String getTimestamp() {
        return timestamp;
    }
    private void setTimestamp(Long tsLong) {
        this.timestamp = tsLong.toString();
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
        return desc;
    }
    public void setDesc(String description) {
        this.desc = description;
    }

    // Getter and Setter for long description
    public String getLongDesc() {
        return longDesc;
    }
    public void setLongDesc(String longDescription) {
        this.longDesc = longDescription;
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
        return null; //(email + ": " + password);
    }
}
