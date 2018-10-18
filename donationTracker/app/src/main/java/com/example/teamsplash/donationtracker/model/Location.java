package com.example.teamsplash.donationtracker.model;

import java.io.Serializable;

public class Location implements Serializable {

    /** Location's name**/
    private String name;

    /** Location's type**/
    private LocationType locationType;

    /** Location's longitude**/
    private double longitude;

    /** Location's latitude**/
    private double latitude;

    /** Location's address**/
    private String address;

    /** Location's city**/
    private String city;

    /** Location's state**/
    private String state;

    /** Location's ZIP**/
    private String zip;

    /** Location's phone number**/
    private String phoneNumber;

    /**
     * creates a new location
     */
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
        if (o == this) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        Location maybeSame = (Location) o;

        return (((Location) o).getName().equals(this.name)
                && ((Location) o).getLocationType().equals(this.locationType)
                && (((Location) o).getLongitude() == this.longitude))
                && ((Location) o).getLatitude() == (this.latitude)
                && ((Location) o).getAddress().equals(this.address)
                && ((Location) o).getCity().equals(this.city)
                && ((Location) o).getState().equals(this.state)
                && ((Location) o).getZip().equals(this.zip)
                && ((Location) o).getPhoneNumber().equals(this.phoneNumber);
    }


    /* *************************************
     * All property getters and setters
     */

    public String getName() {return name;}
    public void setName(String call) {name = call;}

    public String getLocationType() {return locationType.toString();}
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

    /**********************************************/


    @Override
    public String toString() {
        return (name + ", " + address + " : " + longitude + "," + latitude + " : " + phoneNumber);
    }
}