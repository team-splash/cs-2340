package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.Users;
import com.example.teamsplash.donationtracker.R;

public class MainFragment extends Fragment {
    /**
     * @param inflater converts xml into view objects
     * @param container holds the fragments
     * @param savedInstanceState used in clicks of buttons
     * @return view, the view after the xml file change to view after button click
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.main_fragment, container, false);

        fragment.findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOut = new Intent(getActivity(), HomeScreen.class);
                startActivity(logOut);
            }
        });

        fragment.findViewById(R.id.inventoryButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInv = new Intent(getActivity(), InventoryActivity.class);
                startActivity(toInv);
            }
        });



        Users users = Users.getInstance();
        User currentUser = users.getCurrentUser();

        TextView userName = fragment.findViewById(R.id.userName);
        userName.setText(currentUser.getName());

        TextView userEmail = fragment.findViewById(R.id.userEmail);
        userEmail.setText(currentUser.getEmail());

        TextView userType = fragment.findViewById(R.id.userType);
        userType.setText(currentUser.getUserType().toString());

        return fragment;
    }
}
