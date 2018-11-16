package com.example.teamsplash.donationtracker.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.example.teamsplash.donationtracker.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    /**
     * basically map fragment so it can work along the fragments on the bottom of the screen
     */
    public MapFragment() {
        // Required empty public constructor
    }


    /**
     * @param inflater converts xml to view
     * @param container holds the fragmenet
     * @param savedInstanceState used for button clicks to hold it together
     * @return view that will be displayed after button click for the view comes up
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(getActivity()!=null) {
            SupportMapFragment mapFragment =
                    (SupportMapFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        }
        return inflater.inflate(R.layout.activity_maps, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        if(getActivity()!=null) {
//            SupportMapFragment mapFragment =
// (SupportMapFragment) getActivity().getSupportFragmentManager()
//                    .findFragmentById(R.id.map);
//            if (mapFragment != null) {
//                mapFragment.getMapAsync(this);
//            }
//        }
//    }

    /**
     * @param googleMap google map that will come up after this action is called
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("map", "key");


    }
}