package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;

//@SuppressWarnings("ALL")
public class User {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    private final UserType usertype;

    /**
     * @param firstname name of the user
     * @param lastname last name of the user
     * @param email email of the user
     * @param password password of the user
     * @param usertype type of user that the user is
     */
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
    public User(){
        firstname = "k";
        lastname = "l";
        password = "aaaaaaaa";
        email = "k@gmail.com";
        usertype = UserType.USER;
    }

    /**
     * @param o an object that is being compared
     * @return boolean that gives true if items compared are equal
     */
    @Override
    public boolean equals(Object o) {
        return o == this ||  ((User) o).getEmail().equals(this.email) && ((User) o).getPassword().equals(this.password);
               // && ((User) o).getUserType().equals(this.usertype));

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

    /**
     * @return string of the name put together in first and last
     */
    public String getName() { return firstname + " " + lastname; }

    /**
     * @return the email in string form
     */
    // Getter and Setter for email
    public String getEmail() {
        return email;
    }
// --Commented out by Inspection START (11/15/18, 9:08 PM):
//    public void setEmail(String address) {
//        this.email = address;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:08 PM)

    /**
     * @return the password in string form
     */
    // Getter and Setter for password
    public String getPassword() {
        return password;
    }
// --Commented out by Inspection START (11/15/18, 9:08 PM):
//    public void setPassword(String updated) {
//        this.password = updated;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:08 PM)

    /**
     * @return usertype, so what type of user of the user we are seeking
     */
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
        { if( !(tokens.length == 5) ) throw new AssertionError( "Failure message" ); }
        //String actualString = tokens[4].substring(0, tokens[4].length() - 1);
        UserType type = tokens[4].equals("User") ? UserType.USER
                : tokens[4].equals("Location Employee") ? UserType.LOCATION_EMPLOYEE
                : tokens[4].equals("Manager") ? UserType.MANAGER
                : UserType.ADMINISTRATOR;
        User user = new User(tokens[0], tokens[1], tokens[2], tokens[3], type);
        System.out.println("This works, line 123, parseEntry: USER.JAVA");
        return user;
    }

    /**
     * @return string version of the email and password together
     */
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
