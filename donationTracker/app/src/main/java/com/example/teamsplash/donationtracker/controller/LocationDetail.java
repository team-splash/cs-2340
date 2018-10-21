package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.R;


public class LocationDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail_activity);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        Location place = (Location) bd.get("LOCATION");
        String location = place.getName();
        String type = place.getLocationType();
        String longitude = Double.toString(place.getLongitude());
        String latitude = Double.toString(place.getLatitude());
        String address = place.getAddress();
        String city = place.getCity();
        String state = place.getState();
        String zip = place.getZip();
        String phone = place.getPhoneNumber();

        String wholeAddress = address + "\n" + city + ", " + state + ", " + zip;
        TextView loc = (TextView) findViewById(R.id.location);
        loc.setText(wholeAddress);

        TextView phoneNmbr = (TextView) findViewById(R.id.phone);
        phoneNmbr.setText(phone);

        TextView coords = (TextView) findViewById(R.id.coords);
        String fullCoords = latitude + "/" + longitude;
        coords.setText(fullCoords);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(location);

        TextView loctype = (TextView) findViewById(R.id.type);
        loctype.setText(type);
    }

}
