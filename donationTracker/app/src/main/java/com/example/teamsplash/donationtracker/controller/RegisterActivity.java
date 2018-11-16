package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.Users;
import com.example.teamsplash.donationtracker.model.UserType;
import com.example.teamsplash.donationtracker.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText pass;
    private EditText confirmPass;
    private Spinner accountTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        accountTypeSpinner = findViewById(R.id.userType);
        firstname = findViewById(R.id.userFirstName);
        lastname = findViewById(R.id.userLastName);
        email = findViewById(R.id.userEmail);
        pass = findViewById(R.id.userPass);
        confirmPass = findViewById(R.id.userPassConfirm);
        Button submitBtn = findViewById(R.id.registerBtn);
        TextView goToLogin = findViewById(R.id.goToLogin);

        ArrayAdapter<Enum> adapter = new ArrayAdapter<Enum>(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        goToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void submit() {
        boolean cancel = false;
        View focusView;
        View focusView2;
        View focusView3;
        View focusView4;

        firstname.setError(null);
        email.setError(null);
        pass.setError(null);
        confirmPass.setError(null);

        Users users = Users.getInstance();
        String userFirstName = firstname.getText().toString();
        String userLastName = lastname.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = pass.getText().toString();
        String confirmPassword = confirmPass.getText().toString();
        UserType userType = (UserType) accountTypeSpinner.getSelectedItem();

        if (userFirstName.equals("")) {
            firstname.setError(getString(R.string.error_field_required));
            focusView = firstname;
            focusView.requestFocus();
            cancel = true;
        }
        if (userEmail.equals("")) {
            email.setError(getString(R.string.error_field_required));
            focusView2 = email;
            focusView2.requestFocus();
            cancel = true;
        } else if (!isEmailValid(userEmail)) {
            email.setError(getString(R.string.error_invalid_email));
            focusView2 = email;
            focusView2.requestFocus();
            cancel = true;
        }
        if (userPassword.equals("")) {
            pass.setError(getString(R.string.error_field_required));
            focusView3 = pass;
            focusView3.requestFocus();
            cancel = true;
        }
        int MIN_PASSWORD_LENGTH = 8;
        if (confirmPassword.equals("")) {
            confirmPass.setError(getString(R.string.error_field_required));
            focusView4 = confirmPass;
            focusView4.requestFocus();
            cancel = true;
        } else if (userPassword.length() < MIN_PASSWORD_LENGTH) {
            pass.setError(getString(R.string.error_invalid_password));
            focusView3 = pass;
            focusView3.requestFocus();
            cancel = true;
        } else if (!isPasswordValid(userPassword, confirmPassword)) {
            confirmPass.setError(getString(R.string.error_password_mismatch));
            focusView4 = confirmPass;
            focusView4.requestFocus();
            cancel = true;
        }

        //making changes here. First we want to load the file in.
        if (!cancel) {
            User newUser = new User(userFirstName, userLastName, userEmail, userPassword, userType);
            if(users.contains(userEmail, userPassword)) {
                email.setError(getString(R.string.error_user_exists));
                focusView = email;
                focusView.requestFocus();
            } else {
                users.add(newUser); // we add to HashMap, and also want to add to the file at the same time.
                try {
                    PrintWriter newWriter = new PrintWriter(new File(this.getFilesDir(), "userFile"));
                    users.saveAsText(newWriter);
                } catch (FileNotFoundException e) {
                    System.out.println("Failed to add new name to text file. LINE 158:");
                }
                users.setCurrentUser(newUser);
                Intent goToMain = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goToMain);
            }
        }


    }



    // Checks for an @ symbol and a period
    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isPasswordValid(String p1, String p2) {
        return p1.equals(p2);
    }
}
