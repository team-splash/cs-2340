package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.Locations;
import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.Users;


public class LocationDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail_activity);

        Users users = Users.getInstance();
        User currentUser = users.getCurrentUser();

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

        Locations locations = Locations.getInstance();
        locations.setCurrentLocation(place);

        String wholeAddress = address + "\n" + city + ", " + state + ", " + zip;
        TextView loc = findViewById(R.id.location);
        loc.setText(wholeAddress);

        TextView phoneNmbr = findViewById(R.id.phone);
        phoneNmbr.setText(phone);

        TextView coords = findViewById(R.id.coords);
        String fullCoords = latitude + "/" + longitude;
        coords.setText(fullCoords);

        TextView name = findViewById(R.id.name);
        name.setText(location);

        TextView loctype = findViewById(R.id.type);
        loctype.setText(type);

        Button addNewBtn = findViewById(R.id.addItem);
        addNewBtn.setVisibility(View.GONE);
        if (currentUser.getUserType().getRepresentation().equals("Location Employee")) {
            addNewBtn.setVisibility(View.VISIBLE);
        }

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent(LocationDetail.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
    }
}
