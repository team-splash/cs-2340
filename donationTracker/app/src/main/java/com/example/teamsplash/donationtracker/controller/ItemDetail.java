package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.ItemType;
import com.example.teamsplash.donationtracker.model.Location;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_activity);
        Item item = (Item) getIntent().getSerializableExtra("ITEM");

        String name = item.getDesc();
        String description = item.getLongDesc();
        String value = "$" + String.format( "%.2f", item.getValue());
        ItemType type = item.getItemType();
        Location location = item.getLocation();

        TextView title = findViewById(R.id.name);
        TextView desc = findViewById(R.id.description);
        TextView price = findViewById(R.id.value);
        TextView loc = findViewById(R.id.location);
        TextView cat = findViewById(R.id.category);

        title.setText(name);
        desc.setText(description);
        price.setText(value);
        loc.setText(location.getName());
        cat.setText(type.getType());

        Button goBackBtn = findViewById(R.id.backBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
