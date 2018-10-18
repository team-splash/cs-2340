package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.LocationType;
import com.example.teamsplash.donationtracker.model.Locations;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class locationData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);


        toLocations();
        readLocations();

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

    private void readLocations()
    {
        Locations locations = Locations.getInstance();
        if (locations.get().size() != 0)
            return;

        try {
            InputStream stream = getResources().openRawResource(R.raw.locations);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            //Discard header
            reader.readLine();

            locations.readFromCsv(reader);
            reader.close();
        }

        catch (IOException exception) {
            Log.e("cs2340.donationTracker", "Error reading `locations.csv`");

            //Add default location in case of I/O Error
            locations.add(new Location(
                    "AFD Station 4",
                    LocationType.DR,
                    33.75416,
                    -84.37742,
                    "309 EDGEWOOD AVE SE",
                    "Atlanta",
                    "GA",
                    "30330",
                    "(404) 555 - 3456"
            ));
        }
    }

    private void toLocations() {
        Locations locations = Locations.getInstance();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new locationsFragment()).commit();
    }
}