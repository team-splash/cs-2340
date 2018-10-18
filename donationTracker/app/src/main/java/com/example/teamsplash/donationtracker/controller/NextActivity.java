package com.example.teamsplash.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.os.Handler;
import android.os.Message;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Locations;


public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Button buttonLogout = (Button) findViewById(R.id.btn_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Message myMessage = new Message();
                myMessage.obj = "NOTSUCCESS";
                handler.sendMessage(myMessage);
                finish();
            }
        });
        Button buttonFile = (Button) findViewById(R.id.buttonFile);
        buttonFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Message myMessage = new Message();
                myMessage.obj = "NOTSUCCESS";
                handlerFile.sendMessage(myMessage);
                finish();
            }
        });
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String loginmsg = (String) msg.obj;
            if (loginmsg.equals("NOTSUCCESS")) {
                Intent intent = new Intent(com.example.teamsplash.donationtracker.controller.NextActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                removeDialog(0);
            }
        }
    };

    private Handler handlerFile = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String filemsg = (String) msg.obj;
            if (filemsg.equals("NOTSUCCESS")) {
                Intent intent = new Intent(com.example.teamsplash.donationtracker.controller.NextActivity.this, locationsFragment.class);
                startActivity(intent);
                finish();
                removeDialog(0);
            }
        }
    };
}