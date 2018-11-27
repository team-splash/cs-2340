package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * takes care of location objects in a list
 */
public final class Locations implements Serializable {
    private static final Locations _instance = new Locations();

    /**
     * @return locations returns all the different locations when we ask for
     * the instances of them
     */
    public static Locations getInstance() { return _instance; }
    private final List<Location> locations;
    private Locations() {
        locations = new ArrayList<>();
    }

    /**
     * @param place adds the place we want to the location
     */
    @SuppressWarnings("SameReturnValue")
    public void add(Location place) {
        locations.add(place);
    }

    /**
     * @return list of locations that we want
     */
    public List<Location> get() {
        return Collections.unmodifiableList(locations);
    }

    /**
     * @param position of item in list of location we want
     * @return the location that's in that place of the list
     */
    public Location getPosition(int position) {
        return locations.get(position);
    }

    /**
     * @return list of the names of all of the lcoations we are getting
     */
    public Collection<String> getNames() {
        Collection<String> tempLoc = new ArrayList<>();
        for (Location l : locations) {
            tempLoc.add(l.getName());
        }
        return tempLoc;
    }

//    @SuppressWarnings("contains method check")
//    public boolean contains(String name, String address) {
//        for (Location place : locations) {
//            if (place.getName().equals(name) && place.getAddress().equals(address))
//                return true;
//        }
//        return false;
//    }
//    @SuppressWarnings("contains method check")
//    public boolean contains(Location location) {
//        return locations.contains(location);
//    }

    /**
     * @return string form of the location we want
     */
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
     * @param reader a readline reader

     //* @throws IOException -if it doesn't exist
     */
    public void readFromCsv(Scanner reader){
        //locations.clear(); //potentially don't use this as it may lead to overwriting.
        while (reader.hasNext()) {
            String nextLine = reader.nextLine();
            Log.i("THIS IS THE LINE, LINE 91, READ FROM CSV: " + nextLine,
                    "THE MEDIUM IS THE MESSAGE.");
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