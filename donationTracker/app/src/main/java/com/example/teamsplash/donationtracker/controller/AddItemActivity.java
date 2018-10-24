package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.ItemType;
import com.example.teamsplash.donationtracker.model.Items;
import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.Locations;

import org.w3c.dom.Text;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {
    private TextClock clock;
    private TextView currLoc;
    // Location of Item
    private Location currLocation;
    // Short Description of Item
    private EditText desc;
    // Full Description of Item
    private EditText fullDesc;
    // Value (in dollars)
    private EditText value;
    // Category (clothing, hat, kitchen, electronics, household, other)
    private Spinner category;
    // Comments (optional)
    // private EditText comments;
    // Picture (optional)
    // private EditText picture;
    private Button addItemBtn;
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);
        loc = (Location) getIntent().getSerializableExtra("LOCATION");

        clock = findViewById(R.id.textClock);

        Locations locations = Locations.getInstance();
        currLocation = locations.getCurrentLocation();
        currLoc = findViewById(R.id.location_name);
        currLoc.setText(currLocation.getName());

        desc = findViewById(R.id.shortDescription);
        fullDesc = findViewById(R.id.longDescription);
        value = findViewById(R.id.value);

        category = findViewById(R.id.itemType);
        ArrayAdapter<Enum> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        addItemBtn = findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        Items items = Items.getInstance();

        CharSequence time = clock.getFormat12Hour();
        String description = desc.getText().toString();
        String longDescription = fullDesc.getText().toString();
        double val = Double.parseDouble(value.getText().toString());
        ItemType itemtype = (ItemType) category.getSelectedItem();
        Item newItem = new Item(time, currLocation, description, longDescription, val, itemtype);

        if(items.contains(newItem)) {
            Toast.makeText(this.getBaseContext(), "Item already exists",
                    Toast.LENGTH_LONG).show();
        } else {
            items.add(newItem);
            items.setCurrentItem(newItem);
            finish();
        }
    }
}
