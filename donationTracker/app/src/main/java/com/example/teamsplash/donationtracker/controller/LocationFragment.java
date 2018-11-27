package com.example.teamsplash.donationtracker.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.Locations;

/**
 * makes a fragment type for locations. So it can display correctly in the way the app is formated
 */
public class LocationFragment extends Fragment implements Observer {
    @NonNull
    private LayoutInflater inflater;

    private List<Location> locations;
    private ListView list;

    private void update(Locations locationsInstance) {
        locations = locationsInstance.getLocations();
        list.setAdapter(new LocationList(inflater, locations));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof Locations))
            return;

        update((Locations) o);
    }

    /**
     * @param inflater inflates makes layout come to life
     * @param container holsd  fragments
     * @param savedInstanceState used for buttons, holds the state
     * @return view which is the view of our after our button push
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View fragment = inflater.inflate(R.layout.location_fragment, container, false);
        list = fragment.findViewById(R.id.location_list);
        final Locations locationsInstance = Locations.getInstance();
        locationsInstance.addObserver(this);
        update(locationsInstance);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * @param parent adapterview object before click
             * @param view the view we have
             * @param position where on the lsit
             * @param id id of the item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Location itemClicked = locations.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("LOCATION", itemClicked);
                Intent listDetails = new Intent(LocationFragment.this.getActivity(),
                        LocationDetail.class);
                listDetails.putExtras(bundle);
                startActivity(listDetails);
            }
        });
        return fragment;
    }

    @SuppressWarnings({"unused", "FeatureEnvy"})
    private class LocationList extends ArrayAdapter<Location> {

        private final LayoutInflater inflater;
        private final List<Location> locations;

        LocationList(LayoutInflater inflater, List<Location> locations) {
            super(inflater.getContext(), R.layout.location_item_fragment, locations);
            this.inflater = inflater;
            this.locations = locations;
        }

        /**
         * @param position where on the list
         * @param view view object, view of that page
         * @param parent a viewgroup object
         * @return view after button
         */
        @SuppressWarnings("unused")
        @NonNull
        @Override
        public View getView(int position, View view, @NonNull ViewGroup parent) {
            if(view == null){
                //noinspection lint issue if view ever became null,unused
                @SuppressWarnings("unused")
                View rowView = inflater.inflate(R.layout.location_item_fragment,
                        parent, false);
            }
            Location location = locations.get(position);
            View rowView = inflater.inflate(R.layout.location_item_fragment,
                    parent, false);
            TextView name = rowView.findViewById(R.id.location_name);
            TextView address = rowView.findViewById(R.id.location_address);
            TextView cityState = rowView.findViewById(R.id.location_city_state);

            name.setText(location.getName());
            address.setText(location.getStreetAddress());
            cityState.setText(String.format("%s, %s", location.getCityName(), location.getUspsStateCode()));
            return rowView;
        }
    }
}