package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.example.teamsplash.donationtracker.R;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * This is the mainmenu object that does the activities
 * needed for the main menu
 */
@SuppressWarnings({"SpellCheckingInspection", "MagicNumber", "FeatureEnvy"})
public class MainMenu extends AppCompatActivity implements OnMapReadyCallback{
    private final
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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
        if(findViewById(R.id.fragment_container) == null) {
            return;
        }
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
        manager.beginTransaction().replace(R.id.fragment_container,
                new LocationFragment()).commit();
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
       // adding/updating items at the beginning before we even really do anything.
        // this is the most important part. So we instantiate an Items instance.
        Items instance = Items.getInstance();
        File itemsFile = new File(this.getFilesDir(), "newItemFile"); // I
        // get/reaccess/access/create a file where all the Item will be stored as strings.
        try {
            Scanner itemReader = new Scanner(itemsFile); // I create a scanner to read these Strings
            // inside this file.
            instance.loadAsText(itemReader); // I turn the strings into Items, and then add them to
            // the ArrayList of Items. check out Items.loadAsText() for more details.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param googleMap a google map that will be displayed after this call
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}