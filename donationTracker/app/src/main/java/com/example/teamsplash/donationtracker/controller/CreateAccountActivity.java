package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.Users;
import com.example.teamsplash.donationtracker.model.UserType;


public class CreateAccountActivity extends AppCompatActivity {

    private EditText firstNameField;
    private EditText lastNameField;
    private Spinner userTypeField;
    private EditText emailAddressField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    public final int MIN_PASSWORD_LENGTH = 8;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        firstNameField = findViewById(R.id.first_name_field);
        lastNameField = findViewById(R.id.last_name_field);
        userTypeField = findViewById(R.id.user_type_field);
        ArrayAdapter<UserType> userTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UserType.values());
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeField.setAdapter(userTypeAdapter);
        emailAddressField = findViewById(R.id.email_address_field);
        passwordField = findViewById(R.id.password_field);
        confirmPasswordField = findViewById(R.id.confirm_password_field);
        findViewById(R.id.create_account_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateAccount();
            }
        });
        findViewById(R.id.cancel_account_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelPressed();
            }
        });
    }

    /**
     * Button handler for cancel
     */
    public void onCancelPressed() {
        startActivity(new Intent(com.example.teamsplash.donationtracker.controller.CreateAccountActivity.this, LoginActivity.class));
        finish();
    }

    /**
     * Tries to create a new account when the "Create account" button is clicked.
     */
    private void onCreateAccount() {
        Users users = Users.getInstance();
        String firstname = firstNameField.getText().toString();
        String lastname = lastNameField.getText().toString();
        String email = emailAddressField.getText().toString();;
        String password = passwordField.getText().toString();
        String confirmPass = confirmPasswordField.getText().toString();

        // Check if everything during the creation of the account is valid

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailAddressField.setError(getString(R.string.field_required));
            focusView = emailAddressField;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailAddressField.setError(getString(R.string.email_address_invalid));
            focusView = emailAddressField;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (password.length() < MIN_PASSWORD_LENGTH) {
            passwordField.setError(getString(R.string.password_invalid));
            focusView = passwordField;
            cancel = true;
        }
        // TODO fix this password verification??? I don't understand???? -Sara
        /** else if (!isPasswordValid()) {
            confirmPasswordField.setError(getString(R.string.error_mismatched_password));
            focusView = confirmPasswordField;
            cancel = true;
        } **/

        if (cancel) {
            // There was an error; don't attempt to create and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            UserType usertype = (UserType) userTypeField.getSelectedItem();
            User newUser = new User(firstname, lastname, email, password, usertype);

            try {
                users.add(newUser);
                Intent accountCreated = new Intent(com.example.teamsplash.donationtracker.controller.CreateAccountActivity.this, LoginActivity.class);
                startActivity(accountCreated);
            } catch (Users.UserEmailAddressAlreadyRegistered exception) {
                emailAddressField.setError(getString(R.string.email_address_already_registered));
                focusView = emailAddressField;
                focusView.requestFocus();
            }
        }
    }

    // Checks for an @ symbol and a period
    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    // Confirms that the entries in "enter password" and "confirm password" are equivalent
    private boolean isPasswordValid() {
        return passwordField.getText().equals(confirmPasswordField.getText());
    }


}
