package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
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
    private Button cancel;
    private Location loc;
    private Spinner category;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);
        loc = (Location) getIntent().getSerializableExtra("LOCATION");
        submit = findViewById(R.id.addItemBtn);
        submit.setOnClickListener(this);

        cancel = findViewById(R.id.cancelItemBtn);
        cancel.setOnClickListener(this);

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
                name = findViewById(R.id.shortDescription);
                description = findViewById(R.id.longDescription);
                value = findViewById(R.id.value);

                CharSequence time = clock.getFormat24Hour();
                String title = name.getText().toString();
                ItemType itemtype = (ItemType) category.getSelectedItem();

                name.setError(null);
                value.setError(null);
                description.setError(null);

                boolean cancel = false;
                View focusView;
                View focusView2;

                if (title.equals("")) {
                    name.setError(getString(R.string.error_field_required));
                    focusView = name;
                    focusView.requestFocus();
                    cancel = true;
                }
                if (value.getText().toString().equals("")) {
                    value.setError(getString(R.string.error_field_required));
                    focusView2 = value;
                    focusView2.requestFocus();
                    cancel = true;
                }

                if (!cancel) {
                    if (!description.getText().toString().equals("")) {
                        Item item = new Item(time, loc, title, description.getText().toString(),
                                Double.parseDouble(value.getText().toString()), itemtype);
                        Items donated = Items.getInstance();
                        donated.add(item);

                        Intent intent = new Intent(AddItemActivity.this, LocationDetail.class);
                        intent.putExtra("LOCATION", loc);
                        startActivity(intent);
                    } else {
                        Item item = new Item(time, loc, title, "",
                                Double.parseDouble(value.getText().toString()), itemtype);
                        Items donated = Items.getInstance();
                        donated.add(item);

                        Intent intent = new Intent(AddItemActivity.this, LocationDetail.class);
                        intent.putExtra("LOCATION", loc);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.cancelItemBtn :
                finish();
                break;
            default :
                break;
        }
    }

}
