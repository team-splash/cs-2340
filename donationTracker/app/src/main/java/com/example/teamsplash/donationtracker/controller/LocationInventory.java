package com.example.teamsplash.donationtracker.controller;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.Items;
import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.Locations;

public class LocationInventory extends AppCompatActivity {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.location_inventory_fragment, container, false);

        final List<Item> itemList = Items.getInstance().get();

        ItemFragment.ItemList listAdapter = new ItemFragment.ItemList(inflater, itemList);
        final ListView list = fragment.findViewById(R.id.inventory_list);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item itemClicked = itemList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("LOCATION", itemClicked);
                Intent listDetails = new Intent(LocationFragment.this.getActivity(), LocationDetail.class);
                listDetails.putExtras(bundle);

                startActivity(listDetails);
            }
        });

        return fragment;
    }

    private class ItemList extends ArrayAdapter<Item> {

        private final LayoutInflater inflater;
        private final List<Item> items;

        public ItemList(LayoutInflater inflater, List<Item> items) {
            super(inflater.getContext(), R.layout.location_inventory_fragment, items);
            this.inflater = inflater;
            this.items = items;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            Item item = items.get(position);
            View rowView= inflater.inflate(R.layout.location_inventory_fragment, null, true);
            TextView name = rowView.findViewById(R.id.location_name);
            TextView address = rowView.findViewById(R.id.location_address);
            TextView cityState = rowView.findViewById(R.id.location_city_state);

            name.setText(location.getName());
            address.setText(location.getAddress());
            cityState.setText(location.getCity() + ", " + location.getState());
            return rowView;
        }
    }
}