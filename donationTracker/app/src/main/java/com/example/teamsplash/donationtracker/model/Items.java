package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("SameReturnValue")
public class Items implements Serializable {
    private static final Items _instance = new Items();
    public static Items getInstance() {
        return _instance;
    }
    private final ArrayList<Item> ItemData;

    private Items() {
        ItemData = new ArrayList<>();
    }

    public void add(Item item) {
        ItemData.add(item);
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

    public List<Item> getByCategory(ItemType it) {
        List<Item> catItems = new ArrayList<>();
        for (Item item: ItemData) {
            if (item.getItemType().equals(it)) {
                catItems.add(item);
            }
        }
        return catItems;
    }

// --Commented out by Inspection START (11/15/18, 9:13 PM):
//    public boolean contains(Item item) {
//        for (Item i : ItemData) {
//            if (i.getLocation().equals(item.getLocation()) && i.getDesc().equals(item.getDesc())) {
//                return true;
//            }
//        }
//        return false;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:13 PM)


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Item i : ItemData) {
            str.append(i.toString()).append("\n, ");
        }
        return str.toString();
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