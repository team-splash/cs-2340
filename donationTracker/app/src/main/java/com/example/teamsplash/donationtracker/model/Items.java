package com.example.teamsplash.donationtracker.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Items implements Serializable {
    private static final Items _instance = new Items();
    public static Items getInstance() {
        return _instance;
    }
    private ArrayList<Item> ItemData;

    private Items() {
        ItemData = new ArrayList<>();
    }

    public boolean add(Item item) {
        ItemData.add(item);
        return true;
    }

    public List<Item> getLocItems(Location loc) {
        List<Item> locItems = new ArrayList<>();
        for (Item i: ItemData) {
            if (i.getLocation().equals(loc)) {
                locItems.add(i);
            }
        }
        return locItems;
    }

    public boolean contains(Item item) {
        for (Item i : ItemData) {
            if (i.getLocation().equals(item.getLocation()) && i.getDesc().equals(item.getDesc())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        String str = "";
        for (Item i : ItemData) {
            str += i.toString() + "\n, ";
        }
        return str;
    }
}