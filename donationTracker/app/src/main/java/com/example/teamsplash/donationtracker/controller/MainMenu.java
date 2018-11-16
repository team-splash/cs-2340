package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.example.teamsplash.donationtracker.model.Items;
import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.Locations;
import com.example.teamsplash.donationtracker.model.LocationType;
import com.example.teamsplash.donationtracker.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainMenu extends AppCompatActivity implements OnMapReadyCallback{
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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
                case R.id.navigation_map:
                    switchToMaps();
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

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new LocationFragment()).commit();
    }
    private void switchToMaps() {
//        FragmentManager manager = getSupportFragmentManager();
//
//        manager.beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
        Intent toRegister = new Intent(MainMenu.this, MapsActivity.class);
        startActivity(toRegister);
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
        if (locations.get().size() != 0)
            return;
        try {
            InputStream stream = getResources().openRawResource(R.raw.locations);
            Scanner reader = new Scanner(new InputStreamReader(stream, StandardCharsets.UTF_8)); // read from the initial Locations file.
            //Discard header
            reader.nextLine();
            locations.readFromCsv(reader); // adding the locations to the Locations ArrayList.
            reader.close(); // close reader.
            System.out.println(("----------------------------" + " DIVISION IN LINE 93 - 95, MAIN MENU.JAVA"));
            System.out.println(("Size of arrayList after initial adds from old loc file: " + locations.get().size() + ", MAIN MENU JAVA")); // test to see if things work.
            File locationFile = new File(this.getFilesDir(), "locationsFull");
            PrintWriter newWriter = new PrintWriter(locationFile);
            locations.saveAsText(newWriter); // we instantiate/reaccess a new file called locationsFull,
                                                        // which contains all our locations, even past the initial ones found in locations.csv.
                                                         //We add to this file. This is just a safeguard. check out Locations.saveAsText() for more details.
            newWriter.close();
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
        } // commenting this out to see if we can successfully transition away from using the old locations.csv file.
        // I haven't commented out the above, but we probably could. I'd rather not just because I don't want to mess it all up. But
        // technically, we could comment out the entire above block and the code should work fine, because locationsFull contains all locations!
        try {
            File locationFile = new File(this.getFilesDir(), "locationsFull"); //actual new locationsFull file.
            Scanner locationFullScanner = new Scanner(locationFile);
            locations.readFromCsv(locationFullScanner); // re-add the locations from this file into our ArrayList of locations.
            locationFullScanner.close();
            PrintWriter newWriter = new PrintWriter(locationFile);
            locations.saveAsText(newWriter);
            newWriter.close(); // a cycle of copying and recopying for testing's purposes
        } catch (Exception e) {
            e.printStackTrace();
        }

       // adding/updating items at the beginning before we even really do anything.
        // this is the most important part. So we instantiate an Items instance.
        Items instance = Items.getInstance();
        File itemsFile = new File(this.getFilesDir(), "newItemFile"); // I get/reaccess/access/create a file where all the Item will be stored as strings.
        try {
            Scanner itemReader = new Scanner(itemsFile); // I create a scanner to read these Strings inside this file.
            instance.loadAsText(itemReader); // I turn the strings into Items, and then add them to the ArrayList of Items. check out Items.loadAsText() for more details.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}