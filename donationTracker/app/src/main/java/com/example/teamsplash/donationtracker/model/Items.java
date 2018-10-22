package com.example.teamsplash.donationtracker.model;

import java.util.HashMap;

public class Items {

    private static final Items _instance = new Items();
    public static Items getInstance() {
        return _instance;
    }

    public static HashMap<Item, String> ItemData;
    private Item currItem;

    private Items() {
        ItemData = new HashMap<>();
    }

    public boolean add(Item item) {
        ItemData.put(item, item.getTime());
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