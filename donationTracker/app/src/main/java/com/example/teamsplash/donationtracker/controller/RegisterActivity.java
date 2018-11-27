package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.UserType;
import com.example.teamsplash.donationtracker.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * this allows for the submitting of info for registration
 */
@SuppressWarnings("SpellCheckingInspection")


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

        ArrayAdapter<Enum> adapter = new ArrayAdapter<Enum>(
                this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    @SuppressWarnings({"FeatureEnvy", "OverlyComplexMethod", "OverlyLongMethod"})
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
        Editable a = firstname.getText();
        String userFirstName = a.toString();
        Editable b = lastname.getText();
        String userLastName = b.toString();
        Editable c = email.getText();
        String userEmail = c.toString();
        Editable d = pass.getText();
        String userPassword = d.toString();
        Editable f = confirmPass.getText();
        String confirmPassword = f.toString();
        UserType userType = (UserType) accountTypeSpinner.getSelectedItem();

        if ("".equals(userFirstName)) {
            firstname.setError(getString(R.string.error_field_required));
            focusView = firstname;
            focusView.requestFocus();
            cancel = true;
        }
        if ("".equals(userEmail)) {
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
        if ("".equals(userPassword)) {
            pass.setError(getString(R.string.error_field_required));
            focusView3 = pass;
            focusView3.requestFocus();
            cancel = true;
        }
        int MIN_PASSWORD_LENGTH = 8;
        if ("".equals(confirmPassword)) {
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
//            User newUser = new User(userFirstName, userLastName, userEmail, userPassword, userType);
//            if(users.contains(userEmail, userPassword)) {
//                email.setError(getString(R.string.error_user_exists));
//                focusView = email;
//                focusView.requestFocus();
//            } else {
//                users.add(newUser); // we add to HashMap, and also want to add to the file
//                // at the same time.
//                try {
//                    PrintWriter newWriter = new PrintWriter(new File(this.getFilesDir(),
//                            "userFile"));
//                    users.saveAsText(newWriter);
//                } catch (FileNotFoundException e) {
//                    //System.out.println("Failed to add new name to text file. LINE 158:");
//                }
                validateUser(email, pass);
                //users.setCurrentUser(newUser);
                Intent goToMain = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(goToMain);
            }
        }


    private void validateUser(final EditText email, final EditText pass) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String aEmail = email.getText().toString();
                String newEmail = EncodeString(aEmail);
                if (!(dataSnapshot.child("Users").child(newEmail).exists())) {
                    HashMap<String, Object> userData = new HashMap<>();
                    userData.put("first name", firstname.getText().toString());
                    userData.put("last name", lastname.getText().toString());
                    userData.put("email", email.getText().toString());
                    userData.put("password", pass.getText().toString());


                    RootRef.child("Users").child(newEmail).updateChildren(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Congrats! You have a new account.", Toast.LENGTH_SHORT).show();

                                Intent goToMain = new Intent(RegisterActivity.this,
                                        LoginActivity.class);
                                startActivity(goToMain);
                            }

                        }
                    });

                } else {
                    Toast.makeText(RegisterActivity.this, "This email already exists", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, HomeScreen.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public static String EncodeString(String string) {
        return string.replace(".", ",");
    }

    // Checks for an @ symbol and a period
    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isPasswordValid(String p1, String p2) {
        return p1.equals(p2);
    }
}
