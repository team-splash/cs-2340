package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.Model;
import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.R;

public class MainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.main_fragment, container, false);

        fragment.findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOut = new Intent(getActivity(), HomeScreen.class);
                startActivity(logOut);
            }
        });


        User currentUser = Model.getUser();

        TextView userName = fragment.findViewById(R.id.userName);
        userName.setText(currentUser.getFirstName() + " " + currentUser.getLastName());

        TextView userEmail = fragment.findViewById(R.id.userEmail);
        userEmail.setText(currentUser.getEmailAddress());

        TextView userType = fragment.findViewById(R.id.userType);
        userType.setText(currentUser.getType().toString());

        return fragment;
    }
}
