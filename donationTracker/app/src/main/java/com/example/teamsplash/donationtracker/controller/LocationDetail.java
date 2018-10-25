package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Locations;

public class LocationDetail extends AppCompatActivity {
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        location = (Location) bd.get("LOCATION");
        toolbar.setTitle(location.getName());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Locations.getInstance().setCurrentLocation(location);

        final String name = location.getName();
        final String type = location.getLocationType();
        final String longitude = Double.toString(location.getLongitude());
        final String latitude = Double.toString(location.getLatitude());
        final String address = location.getAddress();
        final String city = location.getCity();
        final String state = location.getState();
        final String zip = location.getZip();
        final String phone = location.getPhoneNumber();

        final String wholeAddress = address + "\n" + city + ", " + state + ", " + zip;
        TextView loc = findViewById(R.id.location);
        loc.setText(wholeAddress);

        TextView phoneNmbr = findViewById(R.id.phone);
        phoneNmbr.setText(phone);

        TextView coords = findViewById(R.id.coords);
        String fullCoords = latitude + "/" + longitude;
        coords.setText(fullCoords);

        TextView nameView = findViewById(R.id.name);
        nameView.setText(name);

        TextView loctype = findViewById(R.id.type);
        loctype.setText(type);

        FloatingActionButton donateButton = findViewById(R.id.addButton);

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationDetail.this, AddItemActivity.class);
                intent.putExtra("LOCATION", location);
                startActivity(intent);
            }
        });

        inflateInitialFragment();
    }

    private void inflateInitialFragment() {
        if(findViewById(R.id.item_fragment_container) == null)
            return;
        // set initial fragment layout to the home view
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putSerializable("LOCATION", location);
        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.item_fragment_container, fragment)
                .commit();
    }
}