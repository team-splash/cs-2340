package com.example.teamsplash.donationtracker.controller;

import java.util.List;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.Locations;
import com.example.teamsplash.donationtracker.R;




public class LocationFragment extends Fragment {
    public static Locations locationList;
    SearchView search;
    ListView list;
    LocationList listAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.location_fragment, container, false);

        final List<Location> locationList = Locations.getInstance().get();

        listAdapter = new LocationList(inflater, locationList);
        list = fragment.findViewById(R.id.location_list);
        list.setAdapter(listAdapter);

        search = (SearchView) list.findViewById(R.id.searchableActivity);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String text = (String) list.getItemAtPosition(position);
                Toast.makeText(LocationFragment.this, Toast.LENGTH_SHORT, text).show();
            }
        });



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


    private class LocationList extends ArrayAdapter<Location> {

        private final LayoutInflater inflater;
        private final List<Location> locations;

        public LocationList(LayoutInflater inflater, List<Location> locations) {
            super(inflater.getContext(), R.layout.location_item_fragment, locations);
            this.inflater = inflater;
            this.locations = locations;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            Location location = locations.get(position);
            View rowView = inflater.inflate(R.layout.location_item_fragment, null, true);
            TextView name = rowView.findViewById(R.id.location_name);
            TextView address = rowView.findViewById(R.id.location_address);
            TextView cityState = rowView.findViewById(R.id.location_city_state);

            name.setText(location.getName());
            address.setText(location.getAddress());
            cityState.setText(location.getCity() + ", " + location.getState());
            return rowView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.id.searchableActivity, menu);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchableActivity).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                listAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
}