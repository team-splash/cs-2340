package com.example.teamsplash.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name;
    private EditText description;
    private EditText value;
    private Button submit;
    private Location loc;
    private Spinner category;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);
        loc = (Location) getIntent().getSerializableExtra("LOCATION");
        submit = (Button) findViewById(R.id.addItemBtn);
        submit.setOnClickListener(this);

        TextView currLoc = findViewById(R.id.location_name);
        currLoc.setText(loc.getName());

        category = findViewById(R.id.itemType);
        ArrayAdapter<Enum> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addItemBtn :
                TextClock clock = findViewById(R.id.textClock);
                name = (EditText) findViewById(R.id.shortDescription);
                description = (EditText) findViewById(R.id.longDescription);
                value = findViewById(R.id.value);


                //CharSequence time = clock.getFormat12Hour();
                String title = name.getText().toString();
                ItemType itemtype = (ItemType) category.getSelectedItem();

                if (!title.equals("")) {
                    if (!description.getText().toString().equals("")) {
                        Item item = new Item(loc, title, description.getText().toString(),
                                Double.parseDouble(value.getText().toString()), itemtype);
                        Log.i("Item Created: ", item.toString());
                        Items donated = Items.getInstance();
                        donated.add(item);
                        finish();
                    } else {
                        Item item = new Item(loc, title, "",
                                Double.parseDouble(value.getText().toString()), itemtype);
                        Items donated = Items.getInstance();
                        donated.add(item);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Give the name of your donation item",
                            Toast.LENGTH_LONG).show();
                }
                break;
            default :
                break;
        }
    }

}
