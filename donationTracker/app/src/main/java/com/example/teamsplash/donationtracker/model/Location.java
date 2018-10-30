package com.example.teamsplash.donationtracker.model;

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
        if (o == this) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }

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


    @Override
    public String toString() {
        return (name + ", " + address + " : " + longitude + "," + latitude + " : " + phoneNumber);
    }
}