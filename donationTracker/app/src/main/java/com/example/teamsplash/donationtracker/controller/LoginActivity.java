package com.example.teamsplash.donationtracker.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Model;
import com.example.teamsplash.donationtracker.model.Users.UserEmailAddressNotRegistered;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private com.example.teamsplash.donationtracker.controller.LoginActivity.UserLoginTask authenticationTask = null;

    // UI references.
    private AutoCompleteTextView emailAddressField;
    private EditText passwordField;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        emailAddressField = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        passwordField = (EditText) findViewById(R.id.password);
        passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        Button cleared =(Button) findViewById(R.id.cleared);
        cleared.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                EditText et =(EditText) findViewById(R.id.email);
                et.setText("");
                EditText ft =(EditText) findViewById(R.id.password);
                ft.setText("");
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        ((Button) findViewById(R.id.create_account_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.teamsplash.donationtracker.controller.LoginActivity.this, CreateAccountActivity.class));
                finish();
            }
        });
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(emailAddressField, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    public void onCancel() {
        Toast.makeText(com.example.teamsplash.donationtracker.controller.LoginActivity.this, "User cancelled", Toast.LENGTH_SHORT).show();
    }

    private void clearPasswordField() {
        passwordField.getText().clear();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Do not try to authenticate if an authentication task is already running.
        if (authenticationTask != null)
            return;

        // Reset error messages displayed in the login fields.
        emailAddressField.setError(null);
        passwordField.setError(null);

        // Store values at the time of the login attempt.
        final String emailAddress = emailAddressField.getText().toString();
        final String password = passwordField.getText().toString();

        View firstFieldWithInvalidText = null;

        if (password.isEmpty()) {
            clearPasswordField();
            passwordField.setError(getString(R.string.field_required));
            firstFieldWithInvalidText = passwordField;
        } else {
            if (!(Model.validatePassword(password))) {
                clearPasswordField();
                passwordField.setError(getText(R.string.the_password_was_invalid));
                firstFieldWithInvalidText = passwordField;
            }
        }

        if (emailAddress.isEmpty()) {
            emailAddressField.setError(getString(R.string.field_required));
            firstFieldWithInvalidText = emailAddressField;
        } else {
            if (!(Model.validateEmailAddress(emailAddress))) {
                emailAddressField.setError(getText(R.string.email_address_invalid));
                firstFieldWithInvalidText = emailAddressField;
            }
        }

        if (firstFieldWithInvalidText != null) {
            firstFieldWithInvalidText.requestFocus();
            return;
        }

        showProgress(true);
        authenticationTask = new com.example.teamsplash.donationtracker.controller.LoginActivity.UserLoginTask(emailAddress, password);
        authenticationTask.execute((Void) null);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), com.example.teamsplash.donationtracker.controller.LoginActivity.ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(com.example.teamsplash.donationtracker.controller.LoginActivity.ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(com.example.teamsplash.donationtracker.controller.LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        emailAddressField.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    private enum AuthenticationResult {
        ERROR,
        SUCCESS,
        EMAIL_ADDRESS_NOT_REGISTERED,
        THE_PASSWORD_INCORRECT
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, AuthenticationResult> {
        private final String emailAddress;
        private final String password;

        UserLoginTask(String emailAddress, String password) {
            this.emailAddress = emailAddress;
            this.password = password;
        }

        @Override
        protected AuthenticationResult doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException exception) {
                return AuthenticationResult.ERROR;
            }

            try {
                if (!(Model.checkUserPassword(emailAddress, password)))
                    return AuthenticationResult.THE_PASSWORD_INCORRECT;

                return AuthenticationResult.SUCCESS;
            } catch (UserEmailAddressNotRegistered exception) {
                return AuthenticationResult.EMAIL_ADDRESS_NOT_REGISTERED;
            }
        }

        @Override
        protected void onPostExecute(final AuthenticationResult result) {
            showProgress(false);
            authenticationTask = null;

            switch (result) {
                case ERROR:
                    return;
                case SUCCESS:
                    startActivity(new Intent(com.example.teamsplash.donationtracker.controller.LoginActivity.this, NextActivity.class));
                    finish();
                    return;
                case EMAIL_ADDRESS_NOT_REGISTERED:
                    clearPasswordField();
                    emailAddressField.setError(getText(R.string.email_address_not_registered));
                    emailAddressField.requestFocus();
                    return;
                case THE_PASSWORD_INCORRECT:
                    clearPasswordField();
                    passwordField.setError(getText(R.string.the_password_was_incorrect));
                    passwordField.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
            authenticationTask = null;
        }
    }
}


