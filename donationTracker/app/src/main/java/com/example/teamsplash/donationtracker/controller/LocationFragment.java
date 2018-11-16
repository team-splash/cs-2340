package com.example.teamsplash.donationtracker.controller;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.Locations;
import com.example.teamsplash.donationtracker.R;

public class LocationFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.location_fragment, container, false);

        final List<Location> locationList = Locations.getInstance().get();

        ListAdapter listAdapter = new LocationList(inflater, locationList);
        final ListView list = fragment.findViewById(R.id.location_list);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Location itemClicked = locationList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("LOCATION", itemClicked);
                Intent listDetails = new Intent(LocationFragment.this.getActivity(), LocationDetail.class);
                listDetails.putExtras(bundle);
                startActivity(listDetails);
            }
        });

        return fragment;
    }

    @SuppressWarnings("unused")
    private class LocationList extends ArrayAdapter<Location> {

        private final LayoutInflater inflater;
        private final List<Location> locations;

        LocationList(LayoutInflater inflater, List<Location> locations) {
            super(inflater.getContext(), R.layout.location_item_fragment, locations);
            this.inflater = inflater;
            this.locations = locations;
        }

<<<<<<< HEAD
        @SuppressWarnings("unused")
        @NonNull
        @Override
        public View getView(int position, View view, @NonNull ViewGroup parent) {
            if(view == null){
                //noinspection lint issue if view ever became null,unused
                @SuppressWarnings("unused") View rowView = inflater.inflate(R.layout.location_item_fragment, parent, false);
            }
=======
        @NonNull
        @Override
        public View getView(int position, View view, @NonNull ViewGroup parent) {
>>>>>>> 9917c0a0e2d67b75b2a5c29e7d21950a7063086b
            Location location = locations.get(position);
            View rowView = inflater.inflate(R.layout.location_item_fragment, parent, false);
            TextView name = rowView.findViewById(R.id.location_name);
            TextView address = rowView.findViewById(R.id.location_address);
            TextView cityState = rowView.findViewById(R.id.location_city_state);

            name.setText(location.getName());
            address.setText(location.getAddress());
            cityState.setText(String.format("%s, %s", location.getCity(), location.getState()));
            return rowView;
        }
    }
}