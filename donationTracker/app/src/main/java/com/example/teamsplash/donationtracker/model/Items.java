package com.example.teamsplash.donationtracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Items implements Serializable {
    private static final Items _instance = new Items();
    public static Items getInstance() {
        return _instance;
    }

    public static HashMap<Item, Location> ItemData;
    private Item currItem;

    private Items() {
        ItemData = new HashMap<>();
    }

    public boolean add(Item item) {
        ItemData.put(item, item.getLocation());
        currItem = item;
        return true;
    }

    public Item get(CharSequence ts, Location loc) {
        String timestamp = ts.toString();
        for (Item i: ItemData.keySet()) {
            if (i.getTime().equals(timestamp) && i.getLocation().equals(loc)) {
                return i;
            }
        }
        return null;
    }

    public List<Item> getLocItems(Location loc) {
        List<Item> locItems = new ArrayList<>();
        for (Item i: ItemData.keySet()) {
            if (i.getLocation().equals(loc)) {
                locItems.add(i);
            }
        }
        return locItems;
    }

    public boolean contains(Item item) {
        return ItemData.containsKey(item);
    }

    public boolean contains(CharSequence ts, Location loc) {
        return (get(ts, loc) != null);
    }

    public boolean remove(User item) {
        ItemData.remove(item);
        return true;
    }

    // Getter and setter for current user
    public Item getCurrentItem() {
        return currItem;
    }
    public void setCurrentItem(Item item) {
        currItem = item;
    }

    @Override
    public String toString() {
        String str = "";
        for (Item i : ItemData.keySet()) {
            str += i + "\n, ";
        }
        return str;
    }
}