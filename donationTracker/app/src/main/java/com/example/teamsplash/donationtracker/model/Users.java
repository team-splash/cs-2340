package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Users {

    private static final Users _instance = new Users();
    public static Users getInstance() {
        return _instance;
    }

    private static HashMap<User, String> UserData;
    private User currUser;

    private Users() {
        UserData = new HashMap<>();
        // Hardcode sample users for test login convenience
        UserData.put(new User("First", "Last", "user@abc.com", "123", UserType.USER), "123");
        UserData.put(new User("Chris", "Obando", "chrisjobando@gmail.com", "250797", UserType.ADMINISTRATOR), "250797");
    }

    /**
     * @param user the user we want to add to our keyset for login
     */
    public void add(User user) {
        for (User u : UserData.keySet()) {
            if (user.getEmail().equals(u.getEmail())) {
                return;
            }
        }
        UserData.put(user, user.getPassword());
    }

    /**
     * @param email the email of the user we are retrieving
     * @param password the password of the user we are retrieving
     * @return the user that we are looking for
     */
    public User get(String email, String password) {
        for (User u : UserData.keySet()) {
            Log.i(u.getEmail() + ":" + u.getPassword(), "EACH TIME: LINE 41, USERS.JAVA");
            if (u.getPassword().equals(password) && u.getEmail().equals(email)) {
                String getString = u.getEmail() + " is in the hashMap, and " + u.getPassword() + " is also in the hashMap. LINE 43, USERS.JAVA";
                Log.i(getString, "LINE 44: USERS.JAVA" );
                return u;
            }
        }
        Log.i("WE COULDN'T FIND THE KEY IN THE KEYSET. PROBLEM?", "LINE 48: USERS.JAVA");
        return null;
    }

// --Commented out by Inspection START (11/15/18, 9:10 PM):
//    public boolean contains(User user) {
//
//        for (User u: UserData.keySet()) {
//            if (user.equals(u)) {
//                return true;
//            }
//        }
//        return false;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:10 PM)

    /**
     * @param email the email we are searching for
     * @param password the password we are serching for
     * @return true or false if the email password combo matches
     */
    public boolean contains(String email, String password) {
        return (get(email, password) != null);
    }

// --Commented out by Inspection START (11/15/18, 9:10 PM):
//    public boolean remove(User user) {
//        UserData.remove(user);
//        return true;
//    }
// --Commented out by Inspection STOP (11/15/18, 9:10 PM)

    /**
     * @return the user that is loging in
     */
    // Getter and setter for current user
    public User getCurrentUser() {
        return currUser;
    }
    public void setCurrentUser(User user) {
        currUser = user;
    }

    /**
     * @return string of the information of the user
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (User u : UserData.keySet()) {
            str.append(u).append("\n, ");
        }
        return str.toString();
    }

    /**
     * How we write all the users to a text file, if i'm not mistaken.
     * Read through the User HashMap and add accordingly,
     * using the saveAsText command we defined in the USER class.
     * The PrintWriter will write the contents of a User (a string) to the actual file.
     * We save the data!
     * @param writer - A PrintWriter which is a buffer for an actual file.
     */
    public void saveAsText(PrintWriter writer) {
        Log.d("Users saving: " + UserData.size() , "LINE 85, USERS.JAVA.");
        for (User u: UserData.keySet()) {
            u.saveAsText(writer);
            Log.d("Reached if saveAsText works completely fine.", "LINE 87: USERS.JAVA");
        }
        writer.close();
    }


    /**
     * How do we READ FROM A file? We have a Scanner that reads from the file.
     * and we clear out our HashMap. Then, we add back in again. It's inefficient, but it works.
     * @param reader - A Scanner on our file.
     */
    public void loadAsText(Scanner reader) {
        UserData.clear();
        while (reader.hasNext()) {
            String nextLine = reader.nextLine();
            Log.d(nextLine, "EACH USER LINE, LINE 107: USERS.JAVA");
            User u = User.parseEntry(nextLine);
            Log.d("Check if this is working.", "LINE 102: USERS.JAVA");
            UserData.put(u, u.getPassword());
            String checkSize = String.valueOf(UserData.size());
            Log.d("Size after put operation: " + checkSize, "LINE 105: USERS.JAVA" );
        }
        reader.close();
    }
}
