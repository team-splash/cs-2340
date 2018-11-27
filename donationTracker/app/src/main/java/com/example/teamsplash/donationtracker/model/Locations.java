package com.example.teamsplash.donationtracker.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * takes care of location objects in a list
 */
public final class Locations extends Observable {
    private static final Locations instance = new Locations();

    /**
     * @return locations returns all the different locations when we ask for
     * the instances of them
     */
    public static Locations getInstance() { return instance; }

    private final Map<String, Location> keyLocations;
    private final Object locationsLock;
    private List<Location> locations;
    private final DatabaseReference locationsReference;

    private void updateLocations() {
        synchronized (locationsLock) {
            locations = Collections.unmodifiableList(new ArrayList<>(keyLocations.values()));
        }

        setChanged();
        notifyObservers();
    }

    public List<Location> getLocations() {
        synchronized (locationsLock) {
            return locations;
        }
    }

    private Locations() {
        keyLocations = new HashMap<>();
        locationsLock = new Object();
        locationsReference = FirebaseDatabase.getInstance().getReference().child("locations");
        locationsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                keyLocations.put(dataSnapshot.getKey(), dataSnapshot.getValue(Location.class));
                updateLocations();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                keyLocations.put(dataSnapshot.getKey(), dataSnapshot.getValue(Location.class));
                updateLocations();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                keyLocations.remove(dataSnapshot.getKey());
                updateLocations();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    /**
     * @param place adds the place we want to the location
     */
    @SuppressWarnings("SameReturnValue")
    public void add(Location place) {
        locationsReference.push().setValue(place);
    }
}