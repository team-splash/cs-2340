package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * item object, items has all attributes of item
 */
@SuppressWarnings("SameReturnValue")
public final class Items implements Serializable {
    private static final Items _instance = new Items();

    /**
     * @return instance of that item
     */
    public static Items getInstance() {
        return _instance;
    }
    private final List<Item> ItemData;

    private Items() {
        ItemData = new ArrayList<>();
    }

    /**
     * @param item what we want to add to items from list
     */
    public void add(Item item) {
        ItemData.add(item);
    }

    /**
     * @return the item from the list of items that we want to grab
     */
    public List<Item> get() {
        return Collections.unmodifiableList(ItemData);
    }

    /**
     * @param loc location of the item in the list
     * @return a list of items by location
     */
    public List<Item> getByLocation(Location loc) {
        List<Item> locItems = new ArrayList<>();
        for (Item item: ItemData) {
            Location ofItem = item.getLocation();
            if (ofItem.equals(loc)) {
                locItems.add(item);
            }
        }
        return locItems;
    }

    /**
     * @param it item type of each item in list
     * @return list by category
     */
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
//            if (i.getLocation().equals(item.getLocation()) && i.getDesc().equals(item.getDesc()))
// {
//                return true;
//            }
//        }
//        return false;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:13 PM)


    /**
     * @return string of each item in the lsit
     */
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