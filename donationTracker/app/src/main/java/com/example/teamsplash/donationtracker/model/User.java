package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserType usertype;

    public User(String firstname, String lastname, String email, String password, UserType usertype) {
        String s1 = firstname.substring(0, 1).toUpperCase();
        firstname = s1 + firstname.substring(1);
        this.firstname = firstname;
        if (lastname.length() > 0) {
            String s2 = lastname.substring(0, 1).toUpperCase();
            lastname = s2 + lastname.substring(1);
        }
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        return (((User) o).getFirstName().equals(this.firstname)
                && ((User) o).getLastName().equals(this.lastname)
                && ((User) o).getEmail().equals(this.email)
                && ((User) o).getPassword().equals(this.password)
                && ((User) o).getUserType().equals(this.usertype));

    }

    // Getter and Setter for first name
    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String name) {
        this.firstname = name;
    }

    // Getter and setter for last name
    private String getLastName() {
        return lastname;
    }

    private void setLastName(String name) {
        this.lastname = name;
    }

    public String getName() { return firstname + " " + lastname; }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String address) {
        this.email = address;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String updated) {
        this.password = updated;
    }

    // Getter and Setter for User Type
    public UserType getUserType() {
        return usertype;
    }
    public void setUserType(UserType type) {
        usertype = type;
    }

    /**
     * Using water's code here. Essentially, I want to
     * save the User as a line in a text file.
     *
     * @param writer - a PrintWriter object that we'll be using.
     **/

    public void saveAsText(PrintWriter writer) {
        String fullRep = this.getFullRep();
        Log.d("Full Rep of user to write: " + fullRep, "LINE 98, SAVE AS TEXT: USER.JAVA");
        writer.write(fullRep);
        String newLine = String.format("%n");
        writer.write(newLine);
    }

    /**
     * Using waters' code here again, slightly modified.
     * We want to use the String (if we get it in a file
     * or something) to reconstruct a User.
     * @param line - line we'll be checking
     * @return A user object we can use.
     */
    public static User parseEntry(String line) {
        assert line != null;
        Log.d("We have figured out line isn't null in our parseEntry", "LINE 113: USER.JAVA");
        Log.d("line in file: " + line, "LINE 114, parseEntry: USER.JAVA");
        String[] tokens = line.split(":");
        assert tokens.length == 5;
        String actualString = tokens[4].substring(0, tokens[4].length() - 1);
        UserType type = tokens[4].equals("User") ? UserType.USER
                : tokens[4].equals("Location Employee") ? UserType.LOCATION_EMPLOYEE
                : tokens[4].equals("Manager") ? UserType.MANAGER
                : UserType.ADMINISTRATOR;
        User user = new User(tokens[0], tokens[1], tokens[2], tokens[3], type);
        System.out.println("This works, line 123, parseEntry: USER.JAVA");
        return user;
    }
    @Override
    public String toString() {
        return (email + ": " + password);
    }

    /**
     * we get a full representation of the User. The user shouldn't be able
     * to see any of this, but we can see this because it's important and necessary
     * to storing all data.
     * @return a representation of a User that we can save to a text file.
     */
    public String getFullRep() {
        return (firstname + ":" + lastname + ":" + email + ":" + password + ":" + usertype);
    }
}
