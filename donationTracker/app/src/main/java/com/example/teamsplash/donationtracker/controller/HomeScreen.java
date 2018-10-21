package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.teamsplash.donationtracker.R;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
            Intent intent = new Intent(HomeScreen.this, LoginActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.registerBtn){
            Intent intent = new Intent(HomeScreen.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
