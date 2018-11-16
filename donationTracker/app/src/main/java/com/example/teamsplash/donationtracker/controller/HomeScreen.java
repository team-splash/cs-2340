package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Home screen holds all the stuff to access different parts of the app
 */
@SuppressWarnings("ALL")
public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);
    }

    /**
     * @param v the view item
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
            Users users = Users.getInstance();

            File userFile = new File(this.getFilesDir(), "userFile");
            // either it exists, or it doesn't. Either way it is created/a buffer
            // on the file is created.
            try {
                //FileReader readFile = new FileReader(userFile);
                Scanner br = new Scanner(userFile);
                users.loadAsText(br); // actually reads everything in the file into
                // our userData HashMap.
                //readFile.close(); // closes buffers.
                br.close(); // closes buffers.
            } catch (FileNotFoundException e){
                //System.out.println("Failed to add users to HashMap from text."
                        //+ "LINE 136: RegisterActivity.");
            } // exception handling but this shouldn't happen.
            Intent intent = new Intent(HomeScreen.this, LoginActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.registerBtn){

            Users users = Users.getInstance();
            //System.out.println(this.getFilesDir());
            File userFile = new File(this.getFilesDir(), "userFile"); // either it exists,
            // or it doesn't. Either way it is created/a buffer on the file is created.
            try {
                //FileReader readFile = new FileReader(userFile);
                Scanner br = new Scanner(userFile);
                users.loadAsText(br); // actually reads everything in the file into our
                // userData HashMap.
                //readFile.close(); // closes buffers.
                br.close(); // closes buffers.
            } catch (FileNotFoundException e){
                //System.out.println("Failed to add users to HashMap from text. "
                 //       + "LINE 136: RegisterActivity.");
            } // exception handling but this shouldn't happen.
            Intent intent = new Intent(HomeScreen.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
