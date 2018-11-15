package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Locations implements Serializable {
    private static final Locations _instance = new Locations();
    public static Locations getInstance() { return _instance; }
    private final List<Location> locations;
    private Locations() {
        locations = new ArrayList<>();
    }

    @SuppressWarnings("SameReturnValue")
    public void add(Location place) {
        locations.add(place);
    }
    public List<Location> get() {
        return locations;
    }

    public Location getPosition(int position) {
        return locations.get(position);
    }

    public List<String> getNames() {
        List<String> tempLoc = new ArrayList<>();
        for (Location l : locations) {
            tempLoc.add(l.getName());
        }
        return tempLoc;
    }

    public boolean contains(String name, String address) {
        for (Location place : locations) {
            if (place.getName().equals(name) && place.getAddress().equals(address))
                return true;
        }
        return false;
    }
    public boolean contains(Location location) {
        return locations.contains(location);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Location location : locations) {
            str.append(location).append("\n, ");
        }
        return str.toString();
    }

    /**
     * Save as text method that invokes the Location (singular)
     * method of saveAsText.
     * @param writer - printWriter that is our buffer.
     */
    public void saveAsText(PrintWriter writer) {
        for (Location L: locations) {
            L.saveAsText(writer);
        }
        writer.close();
    }
    /*private static final int CSV_INDEX_NAME			= 1;
    private static final int CSV_INDEX_LATITUDE		= 2;
    private static final int CSV_INDEX_LONGITUDE	= 3;
    private static final int CSV_INDEX_ADDRESS		= 4;
    private static final int CSV_INDEX_CITY			= 5;
    private static final int CSV_INDEX_STATE		= 6;
    private static final int CSV_INDEX_ZIP			= 7;
    private static final int CSV_INDEX_TYPE			= 8;
    private static final int CSV_INDEX_PHONE		= 9;*/

    /**
     * Basically the loadAsText method from User and Item.
     * But I don't want to mess things up so I'm not changing the name of the method,
     * and in some ways I'm not really amending its functionality, but I'm making it more in line
     * with what has been written for persistence in User(s) and soon Item(s).
     * @param reader - Scanner object that reads our File.
     * @throws IOException
     */
    public void readFromCsv(Scanner reader) {
        //locations.clear(); //potentially don't use this as it may lead to overwriting.
        while (reader.hasNext()) {
            String nextLine = reader.nextLine();
            Log.i("THIS IS THE LINE, LINE 91, READ FROM CSV: " + nextLine, "THE MEDIUM IS THE MESSAGE.");
            Location newLoc = Location.parseEntry(nextLine);
            locations.add(newLoc); // add to ArrayList.
        }
        /*while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            String name = tokens[CSV_INDEX_NAME];
            double latitude = Double.parseDouble(tokens[CSV_INDEX_LATITUDE]);
            double longitude = Double.parseDouble(tokens[CSV_INDEX_LONGITUDE]);
            String address = tokens[CSV_INDEX_ADDRESS];
            String city = tokens[CSV_INDEX_CITY];
            String state = tokens[CSV_INDEX_STATE];
            String zip = tokens[CSV_INDEX_ZIP];
            LocationType type = LocationType.fromString(tokens[CSV_INDEX_TYPE]);
            String phone = tokens[CSV_INDEX_PHONE];
            Location location = new Location(
                    name,
                    type,
                    latitude,
                    longitude,
                    address,
                    city,
                    state,
                    zip,
                    phone
            );
            this.add(location);
        }*/
    }
}