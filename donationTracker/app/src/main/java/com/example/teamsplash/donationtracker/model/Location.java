package com.example.teamsplash.donationtracker.model;

public class Location {

    private String name;
    private String type;
    private double longitude;
    private double latitude;
    private String address;
    private String phone;

    public Location(String name, String type, double longitude, double latitude, String address, String phone) {
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
