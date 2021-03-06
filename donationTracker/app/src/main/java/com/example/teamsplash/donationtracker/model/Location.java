package com.example.teamsplash.donationtracker.model;

import android.annotation.SuppressLint;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Observable;

//@SuppressWarnings("ALL")
public class Location extends Observable implements Serializable {

    private final String name;
    private final LocationType locationType;
    private final double longitude;
    private final double latitude;
    private final String streetAddress;
    private final String cityName;
    private final String uspsStateCode;
    private final String zipCode;
    private final String phoneNumber;

    Location() {
        this(null, null, 0.0, 0.0, null, null, null, null, null);
    }

    /**
     * @param name name of the location of donation center
     * @param locationType type of donation center
     * @param longitude longitude of donation center
     * @param latitude latitude of donation center
     * @param streetAddress streetAddress of donation center
     * @param cityName cityName donation center is located in
     * @param uspsStateCode uspsStateCode donation location is in
     * @param zipCode zipCode code of donation center
     * @param phoneNumber phone number of donation center
     */
    public Location(String name, LocationType locationType, double longitude, double latitude,
                    String streetAddress, String cityName, String uspsStateCode, String zipCode, String phoneNumber) {
        this.name = name;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.streetAddress = streetAddress;
        this.cityName = cityName;
        this.uspsStateCode = uspsStateCode;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param o the object we want to make comparisons on
     * @return if they are equal, true if they are, false if not
     */
    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof Location && (((Location) o).getName().equals(this.name) && ((Location) o).getLocationType().equals(this.locationType) && (((Location) o).getLongitude() == this.longitude)) && ((Location) o).getLatitude() == (this.latitude) && ((Location) o).getStreetAddress().equals(this.streetAddress) && ((Location) o).getCityName().equals(this.cityName) && ((Location) o).getUspsStateCode().equals(this.uspsStateCode) && ((Location) o).getZipCode().equals(this.zipCode) && ((Location) o).getPhoneNumber().equals(this.phoneNumber);

    }

    /**
     * @return name of location we are getting
     */
    public String getName() {return name;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setName(String call) {name = call;}

    /**
     * @return location type of location we are getting
     */
    public LocationType getLocationType() {return locationType;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setLocationType(LocationType type) {locationType = type;}

    /**
     * @return longitude of of location we are getting
     */
    public double getLongitude() {return longitude;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setLongitude(double longy) {longitude = longy;}

    /**
     * @return latitude of of location we are getting
     */
    public double getLatitude() {return latitude;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setLatitude(double laty) {latitude = laty;}

    /**
     * @return streetAddress of of location we are getting
     */
    public String getStreetAddress() {return streetAddress;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setAddress(String place) {streetAddress = place;}

    /**
     * @return phone number of location we are getting
     */
    public String getPhoneNumber() {return phoneNumber;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setPhoneNumber(String num) {phoneNumber = num;}

    /**
     * @return cityName of location we are getting
     */
    public String getCityName() {return cityName;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setCity(String c) {cityName = c;}

    /**
     * @return uspsStateCode of location we are getting
     */
    public String getUspsStateCode() {return uspsStateCode;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setState(String s) {uspsStateCode = s;}

    /**
     * @return zipCode of location we are getting
     */
    public String getZipCode() {return zipCode;}
    // --Commented out by Inspection (11/15/18, 9:00 PM):public void setZip(String z) {zipCode = z;}

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
    @SuppressLint("Assert")
    public static Location parseEntry(String line) {
        assert line != null;
        //Log.d("We have figured out line isn't null in our parseEntry", "LINE 113: USER.JAVA");
        //Log.d("line in file: " + line, "LINE 114, parseEntry: USER.JAVA");
        String[] tokens = line.split(",");
        assert !(tokens.length == 10);


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

    /**
     * @return string of name, streetAddress, long, lat, and phone number attributes of location
     */
    @Override
    public String toString() {
        return (name + ", " + streetAddress + " : " + longitude + "," + latitude + " : " + phoneNumber);
    }

    /**
     * @return all of the attributes of the location into one string
     */
    public String getFullRep() {
        return "x" + "," + name + "," + latitude + "," + longitude + "," + streetAddress + "," + cityName + "," + uspsStateCode + "," +

               zipCode + "," + locationType + "," + phoneNumber; // added the "x" so that all numberings for Location were consistent.
    }
}