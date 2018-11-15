package com.example.teamsplash.donationtracker.model;

import java.io.PrintWriter;
import java.io.Serializable;

public class Location implements Serializable {

    private String name;
    private LocationType locationType;
    private double longitude;
    private double latitude;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public Location(String name, LocationType locationType, double longitude, double latitude,
                    String address, String city, String state, String zip, String phoneNumber) {
        this.name = name;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof Location && (((Location) o).getName().equals(this.name) && ((Location) o).getLocationType().equals(this.locationType) && (((Location) o).getLongitude() == this.longitude)) && ((Location) o).getLatitude() == (this.latitude) && ((Location) o).getAddress().equals(this.address) && ((Location) o).getCity().equals(this.city) && ((Location) o).getState().equals(this.state) && ((Location) o).getZip().equals(this.zip) && ((Location) o).getPhoneNumber().equals(this.phoneNumber);

    }

    public String getName() {return name;}
    public void setName(String call) {name = call;}

    public LocationType getLocationType() {return locationType;}
    public void setLocationType(LocationType type) {locationType = type;}

    public double getLongitude() {return longitude;}
    public void setLongitude(double longy) {longitude = longy;}

    public double getLatitude() {return latitude;}
    public void setLatitude(double laty) {latitude = laty;}

    public String getAddress() {return address;}
    public void setAddress(String place) {address = place;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String num) {phoneNumber = num;}

    public String getCity() {return city;}
    public void setCity(String c) {city = c;}

    public String getState() {return state;}
    public void setState(String s) {state = s;}

    public String getZip() {return zip;}
    public void setZip(String z) {zip = z;}

    /**
     * Using water's code here. Essentially, I want to
     * save the User as a line in a text file.
     *
     * @param writer - a PrintWriter object that we'll be using.
     **/

    public void saveAsText(PrintWriter writer) {
        String fullRep = this.getFullRep();
        System.out.println("FULL REP EACH TIME: LINE 89, SAVE AS TEXT, LOCATION.JAVA: " + fullRep);
        //Log.d("Full Rep of user to write: " + fullRep, "LINE 98, SAVE AS TEXT: USER.JAVA");
        writer.write(fullRep);
        String newLine = String.format("%n");
        writer.write(newLine);
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
    /**
     * Location parseEntry method. Very similar to methods in other classes such as Item and Users.
     * We use fromString() found in LocationType to make our lives easier. Unfortunately, as we'll soon see,
     * it's really not easy to actually get the location out of the Item data.
     * @param line - the line that represents our Location, in string form.
     * @return an actual Location object.
     */
    public static Location parseEntry(String line) {
        assert line != null;
        //Log.d("We have figured out line isn't null in our parseEntry", "LINE 113: USER.JAVA");
        //Log.d("line in file: " + line, "LINE 114, parseEntry: USER.JAVA");
        String[] tokens = line.split(",");
        assert tokens.length == 10;
        String name = tokens[CSV_INDEX_NAME]; // 1
        double latitude = Double.parseDouble(tokens[CSV_INDEX_LATITUDE]); // 2
        double longitude = Double.parseDouble(tokens[CSV_INDEX_LONGITUDE]); // 3
        String address = tokens[CSV_INDEX_ADDRESS]; //4
        String city = tokens[CSV_INDEX_CITY]; //5
        String state = tokens[CSV_INDEX_STATE]; //6
        String zip = tokens[CSV_INDEX_ZIP]; // 7
        LocationType type = LocationType.fromString(tokens[CSV_INDEX_TYPE]); //8
        String phone = tokens[CSV_INDEX_PHONE]; // 9
        //String actualString = tokens[8].substring(0, tokens[8].length() - 1);
        //LocationType actualType = LocationType.fromString(tokens[1]);
        //System.out.println("This works, line 123, parseEntry: USER.JAVA");
        return new Location(name, type, latitude, longitude,
        address, city, state, zip, phone);
    }

    @Override
    public String toString() {
        return (name + ", " + address + " : " + longitude + "," + latitude + " : " + phoneNumber);
    }

    public String getFullRep() {
        return "x" + "," + name + "," + latitude + "," + longitude + "," + address + "," + city + "," + state + "," + zip + "," + locationType + "," + phoneNumber; // added the "x" so that all numberings for Location were consistent.
    }
}