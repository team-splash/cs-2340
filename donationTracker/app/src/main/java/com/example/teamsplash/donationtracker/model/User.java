package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;

public class User {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    private final UserType usertype;

    public User(String firstname, String lastname, String email, String password, UserType usertype) {
        String s1 = firstname.substring(0, 1).toUpperCase();
        this.firstname = s1 + firstname.substring(1);
        if (!lastname.isEmpty()) {
            String s2 = lastname.substring(0, 1).toUpperCase();
            this.lastname = s2 + lastname.substring(1);
        }
        this.email = email;
        this.password = password;
        this.usertype = usertype;
    }

    @Override
    public boolean equals(Object o) {
<<<<<<< HEAD
        return o == this || o instanceof User && (((User) o).getFirstName().equals(this.firstname) && ((User) o).getLastName().equals(this.lastname) && ((User) o).getEmail().equals(this.email) && ((User) o).getPassword().equals(this.password) && ((User) o).getUserType().equals(this.usertype));
=======
        return o == this || (o instanceof User) && (((User) o).getFirstName().equals(this.firstname) && ((User) o).getLastName().equals(this.lastname) && ((User) o).getEmail().equals(this.email) && ((User) o).getPassword().equals(this.password) && ((User) o).getUserType().equals(this.usertype));
>>>>>>> 9917c0a0e2d67b75b2a5c29e7d21950a7063086b

    }

    // Getter and Setter for first name
    private String getFirstName() {
        return firstname;
    }

// --Commented out by Inspection START (11/15/18, 9:08 PM):
//    public void setFirstName(String name) {
//        this.firstname = name;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:08 PM)

    // Getter and setter for last name
    private String getLastName() {
        return lastname;
    }

// --Commented out by Inspection START (11/15/18, 9:08 PM):
//    private void setLastName(String name) {
//        this.lastname = name;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:08 PM)

    public String getName() { return firstname + " " + lastname; }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }
// --Commented out by Inspection START (11/15/18, 9:08 PM):
//    public void setEmail(String address) {
//        this.email = address;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:08 PM)

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }
// --Commented out by Inspection START (11/15/18, 9:08 PM):
//    public void setPassword(String updated) {
//        this.password = updated;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:08 PM)

    // Getter and Setter for User Type
    public UserType getUserType() {
        return usertype;
    }
// --Commented out by Inspection START (11/15/18, 9:08 PM):
//    public void setUserType(UserType type) {
//        usertype = type;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:08 PM)

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
<<<<<<< HEAD
        { if( !(tokens.length == 5) ) throw new AssertionError( "Failure message" ); }
        //String actualString = tokens[4].substring(0, tokens[4].length() - 1);
        UserType type = tokens[4].equals("User") ? UserType.USER
                : tokens[4].equals("Location Employee") ? UserType.LOCATION_EMPLOYEE
                : tokens[4].equals("Manager") ? UserType.MANAGER
                : UserType.ADMINISTRATOR;
=======
        String actualString = tokens[4].substring(0, tokens[4].length() - 1);
        UserType type = "User".equals(tokens[4]) ? UserType.USER
                : ("Location Employee".equals(tokens[4]) ? UserType.LOCATION_EMPLOYEE
                : ("Manager".equals(tokens[4]) ? UserType.MANAGER
                : UserType.ADMINISTRATOR));
>>>>>>> 9917c0a0e2d67b75b2a5c29e7d21950a7063086b
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
    private String getFullRep() {
        return (firstname + ":" + lastname + ":" + email + ":" + password + ":" + usertype);
    }
}
