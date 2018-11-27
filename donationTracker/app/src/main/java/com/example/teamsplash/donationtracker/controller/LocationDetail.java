package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.UserType;
import com.example.teamsplash.donationtracker.model.Users;

import java.util.Objects;

/**
 * Location detail keeps track of the locations in a listAdapter.
 */
//@SuppressWarnings("ALL")
public class LocationDetail extends AppCompatActivity {

    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        location = (Location) Objects.requireNonNull(bd).get("LOCATION");
        toolbar.setTitle(Objects.requireNonNull(location).getName());

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

       // @SuppressWarnings("locations name") final String name = location.getName();
        final String type = location.getLocationType().toString();
        final String longitude = Double.toString(location.getLongitude());
        final String latitude = Double.toString(location.getLatitude());
        final String address = location.getStreetAddress();
        final String city = location.getCityName();
        final String state = location.getUspsStateCode();
        final String zip = location.getZipCode();
        final String phone = location.getPhoneNumber();

        final String wholeAddress = address + "\n" + city + ", " + state + ", " + zip;
        TextView loc = findViewById(R.id.location);
        loc.setText(wholeAddress);

        TextView phoneNmbr = findViewById(R.id.phone);
        phoneNmbr.setText(phone);

        TextView coords = findViewById(R.id.coords);
        String fullCoords = latitude + "/" + longitude;
        coords.setText(fullCoords);

        TextView loctype = findViewById(R.id.type);
        loctype.setText(type);

        FloatingActionButton addBtn = findViewById(R.id.addButton);

        UserType currUserType = Users.getInstance().getCurrentUser().getUserType();
        if (currUserType == UserType.USER) {
            findViewById(R.id.addButton).setVisibility(View.GONE);
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v view object
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationDetail.this, AddItemActivity.class);
                intent.putExtra("LOCATION", location);
                startActivity(intent);
            }
        });
        //inflateInitialFragment();
    }


}