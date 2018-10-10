package com.example.teamsplash.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.os.Handler;
import android.os.Message;


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
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String loginmsg=(String)msg.obj;
            if(loginmsg.equals("NOTSUCCESS")) {
                Intent intent = new Intent(NextActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                removeDialog(0);
            }
        }
    };
}