package com.example.teamsplash.donationtracker.controller;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.Locations;
import com.example.teamsplash.donationtracker.model.LocationType;
import com.example.teamsplash.donationtracker.R;

public class MainMenu extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchToHome();
                    return true;
                case R.id.navigation_locations:
                    switchToLocations();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        inflateInitialFragment();
        readLocations();
    }

    private void inflateInitialFragment() {
        if(findViewById(R.id.fragment_container) == null)
            return;
        // Set initial fragment layout to the home view
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new MainFragment())
                .commit();
    }

    private void switchToHome() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
    }

    private void switchToLocations() {
        Locations locations = Locations.getInstance();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new LocationFragment()).commit();
    }

    /**
     * This method is very important and accomplishes some major functions.
     * First, it uses both an initial locations.csv file and a locationsFull file to
     * get all locations loaded into the ArrayList of Locations.
     * Then, it also loads all items added as well.
     */
    private void readLocations() {
        Locations locations = Locations.getInstance();
        // initial adding from locations.csv.
        /*if (locations.get().size() != 0)
            return;
        try {
            InputStream stream = getResources().openRawResource(R.raw.locations);
            Scanner reader = new Scanner(new InputStreamReader(stream, StandardCharsets.UTF_8));
            //Discard header
            reader.nextLine();
            locations.readFromCsv(reader);
            reader.close();
        } // read from original CSV file, locations. But we need to transition away from this file. So we're really reading from one
        // original file and then using another file... which you'll see past this catch block.

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
        }*/ // commenting this out to see if we can successfully transition away from using the old locations.csv file.
        try {
            File locationFile = new File(this.getFilesDir(), "locationsFull"); //actual new locationsFull file.
            Scanner locationFullScanner = new Scanner(locationFile);
            locations.readFromCsv(locationFullScanner);
            locationFullScanner.close();
            PrintWriter newWriter = new PrintWriter(locationFile);
            locations.saveAsText(newWriter);
            newWriter.close(); // we add from a blank file and then write to a blank file, so we keep a copy of all our locations after all.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}