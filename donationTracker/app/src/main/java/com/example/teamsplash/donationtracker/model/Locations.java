package com.example.teamsplash.donationtracker.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Locations implements Serializable {
    private static final Locations _instance = new Locations();
    public static Locations getInstance() { return _instance; }
    private List<Location> locations;
    private Locations() {
        locations = new ArrayList<>();
    }

    public boolean add(Location place) {
        locations.add(place);
        return true;
    }
    public List<Location> get() {
        return locations;
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
        String str = "";
        for (Location location : locations) {
            str += location + "\n, ";
        }
        return str;
    }

    private static final int CSV_INDEX_NAME			= 1;
    private static final int CSV_INDEX_LATITUDE		= 2;
    private static final int CSV_INDEX_LONGITUDE	= 3;
    private static final int CSV_INDEX_ADDRESS		= 4;
    private static final int CSV_INDEX_CITY			= 5;
    private static final int CSV_INDEX_STATE		= 6;
    private static final int CSV_INDEX_ZIP			= 7;
    private static final int CSV_INDEX_TYPE			= 8;
    private static final int CSV_INDEX_PHONE		= 9;

    public void readFromCsv(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
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
        }
    }


}