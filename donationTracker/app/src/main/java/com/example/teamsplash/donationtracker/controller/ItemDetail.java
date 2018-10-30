package com.example.teamsplash.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.Location;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_activity);
        Item item = (Item) getIntent().getSerializableExtra("ITEM");

        String name = item.getDesc();
        String description = item.getLongDesc();
        String value = Double.toString(item.getValue());
        Location location = item.getLocation();

        TextView title = findViewById(R.id.name);
        TextView desc = findViewById(R.id.description);
        TextView price = findViewById(R.id.value);
        TextView loc = findViewById(R.id.location);

        title.setText(name);
        desc.setText(description);
        price.setText(value);
        loc.setText(location.toString());
    }
}
