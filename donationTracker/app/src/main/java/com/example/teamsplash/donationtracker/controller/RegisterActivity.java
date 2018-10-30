package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.Users;
import com.example.teamsplash.donationtracker.model.UserType;
import com.example.teamsplash.donationtracker.R;

public class RegisterActivity extends AppCompatActivity {
    public final int MIN_PASSWORD_LENGTH = 8;
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText pass;
    private EditText confirmPass;
    private Spinner accountTypeSpinner;
    private Button submitBtn;
    private TextView goToLogin;

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
        submitBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.goToLogin);

        ArrayAdapter<Enum> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
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
        View focusView = null;

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

        if (userFirstName == "") {
            firstname.setError(getString(R.string.error_field_required));
            focusView = firstname;
            cancel = true;
        } else if (userEmail == "") {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        } else if (userPassword == "") {
            pass.setError(getString(R.string.error_field_required));
            focusView = pass;
            cancel = true;
        } else if (confirmPassword == "") {
            confirmPass.setError(getString(R.string.error_field_required));
            focusView = confirmPass;
            cancel = true;
        } else if (userPassword.length() < MIN_PASSWORD_LENGTH) {
            pass.setError(getString(R.string.error_invalid_password));
            focusView = pass;
            cancel = true;
        } else if (!isEmailValid(userEmail)) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        } else if (!isPasswordValid(userPassword, confirmPassword)) {
            confirmPass.setError(getString(R.string.error_password_mismatch));
            focusView = confirmPass;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            User newUser = new User(userFirstName, userLastName, userEmail, userPassword, userType);
            if(users.contains(newUser)) {
                email.setError(getString(R.string.error_user_exists));
                focusView = email;
                focusView.requestFocus();
            } else {
                users.add(newUser);
                users.setCurrentUser(newUser);
                Intent goToMain = new Intent(RegisterActivity.this, MainMenu.class);
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
