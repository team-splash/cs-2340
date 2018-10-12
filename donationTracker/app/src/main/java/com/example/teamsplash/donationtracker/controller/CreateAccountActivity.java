package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Model;
import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.UserType;
import com.example.teamsplash.donationtracker.model.Users.UserEmailAddressAlreadyRegistered;


public class CreateAccountActivity extends AppCompatActivity {
    private EditText firstNameField;
    private EditText lastNameField;
    private Spinner userTypeField;
    private EditText emailAddressField;
    private EditText passwordField;
    private EditText confirmPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                onCancel();
            }
        });
    }

    private void clearPasswordFields() {
        passwordField.getText().clear();
        confirmPasswordField.getText().clear();
    }

    /**
     * Tries to create a new account when the "Create account" button is clicked.
     */
    private void onCreateAccount() {
        // Reset error messages displayed in the registration fields.
        firstNameField.setError(null);
        lastNameField.setError(null);
        emailAddressField.setError(null);
        passwordField.setError(null);
        confirmPasswordField.setError(null);

        final String firstName = firstNameField.getText().toString();
        final String lastName = lastNameField.getText().toString();
        UserType userType = (UserType) userTypeField.getSelectedItem();
        final String emailAddress = emailAddressField.getText().toString();
        final String password = passwordField.getText().toString();
        final String confirmPassword = confirmPasswordField.getText().toString();

        View firstFieldWithInvalidText = null;

        if (password.isEmpty()) {
            clearPasswordFields();
            passwordField.setError(getText(R.string.field_required));
            firstFieldWithInvalidText = passwordField;
        } else {
            if (!(password.equals(confirmPassword))) {
                clearPasswordFields();
                passwordField.setError(getText(R.string.the_passwords_were_not_matching));
                firstFieldWithInvalidText = passwordField;
            } else {
                if (!(Model.validatePassword(password))) {
                    clearPasswordFields();
                    passwordField.setError(getText(R.string.the_password_was_invalid));
                    firstFieldWithInvalidText = passwordField;
                }
            }
        }

        if (emailAddress.isEmpty()) {
            emailAddressField.setError(getText(R.string.field_required));
            firstFieldWithInvalidText = emailAddressField;
        } else {
            if (!(Model.validateEmailAddress(emailAddress))) {
                emailAddressField.setError(getText(R.string.email_address_invalid));
                firstFieldWithInvalidText = emailAddressField;
            }
        }

        if (lastName.isEmpty()) {
            lastNameField.setError(getText(R.string.field_required));
            firstFieldWithInvalidText = lastNameField;
        }

        if (firstName.isEmpty()) {
            firstNameField.setError(getText(R.string.field_required));
            firstFieldWithInvalidText = firstNameField;
        }

        if (firstFieldWithInvalidText != null) {
            firstFieldWithInvalidText.requestFocus();
            return;
        }

        try {
            Model.addUser(new User(firstName, lastName, emailAddress, password, userType));
            startActivity(new Intent(com.example.teamsplash.donationtracker.controller.CreateAccountActivity.this, LoginActivity.class));
            finish();
        } catch (UserEmailAddressAlreadyRegistered exception) {
            emailAddressField.setError(getString(R.string.email_address_already_registered));
            emailAddressField.requestFocus();
        }
    }

    /**
     * Button handler for cancel
     */
    public void onCancel() {
        startActivity(new Intent(com.example.teamsplash.donationtracker.controller.CreateAccountActivity.this, LoginActivity.class));
        finish();
    }
}
