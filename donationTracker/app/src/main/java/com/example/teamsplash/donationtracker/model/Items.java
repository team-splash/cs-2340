package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public List<Item> get() {
        return ItemData;
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

    /**
     * saveAsText method for the ArrayList in Locations - we call
     * the Location (singular) saveAsText method.
     * @param writer - the printWriter which represents the file we are editing.
     */
    public void saveAsText(PrintWriter writer) {
        for (Item i: ItemData) {
            i.saveAsText(writer);
        }
        writer.close();
    }

    /**
     * loadAsText method, which invokes the parseEntry method for Items
     * and adds Items into the ArrayList.
     * @param reader - a Scanner reader working on a file of Locations.
     */
    public void loadAsText(Scanner reader) {
        ItemData.clear();
        while (reader.hasNext()) {
            String nextLine = reader.nextLine();
            Item newItem = Item.parseEntry(nextLine);
            ItemData.add(newItem);
            String checkSize = String.valueOf(ItemData.size());
            Log.d("Size after add operation: " + checkSize, "LINE 80: ITEMS.JAVA");
        }
        reader.close();
    }
}