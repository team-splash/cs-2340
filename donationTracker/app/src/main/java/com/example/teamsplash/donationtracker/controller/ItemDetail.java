package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.ItemType;
import com.example.teamsplash.donationtracker.model.Items;
import com.example.teamsplash.donationtracker.model.Location;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_activity);

        Items items = Items.getInstance();

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        Item item = (Item) bd.get("ITEM");

        String shortdesc = item.getDesc();
        String longdesc = item.getLongDesc();
        double val = item.getValue();
        Location loc = item.getLocation();
        ItemType itemtype = item.getItemType();

        items.setCurrentItem(item);

        TextView location = findViewById(R.id.location);
        location.setText(loc.toString());

        TextView shortDesc = findViewById(R.id.shortDescription);
        shortDesc.setText(shortdesc);

        TextView price = findViewById(R.id.price);
        price.setText(Double.toString(val));

        TextView cat = findViewById(R.id.category);
        cat.setText(itemtype.toString());

        TextView fullDesc = findViewById(R.id.fullDescription);
        fullDesc.setText(longdesc);
    }
}

