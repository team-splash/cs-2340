package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
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

    public List<Item> getByLocation(Location loc) {

        List<Item> locItems = new ArrayList<>();
        for (Item item: ItemData) {
            if (item.getLocation().equals(loc)) {
                locItems.add(item);
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