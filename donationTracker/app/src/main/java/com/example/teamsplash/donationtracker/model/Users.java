package com.example.teamsplash.donationtracker.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Users {

    private static final Users _instance = new Users();
    public static Users getInstance() {
        return _instance;
    }

    public static HashMap<User, String> UserData;
    private User currUser;

    private Users() {
        UserData = new HashMap<>();
        // Hardcode sample users for test login convenience
        UserData.put(new User("First", "Last", "user@abc.com", "123", UserType.USER), "123");
        UserData.put(new User("Chris", "Obando", "chrisjobando@gmail.com", "250797", UserType.ADMINISTRATOR), "250797");
    }

    public boolean add(User user) {
        for (User u : UserData.keySet()) {
            if (user.getEmail().equals(u.getEmail())) {
                return false;
            }
        }
        UserData.put(user, user.getPassword());
        return true;
    }

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

    public boolean contains(User user) {

        for (User u: UserData.keySet()) {
            if (user.equals(u)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String email, String password) {
        return (get(email, password) != null);
    }

    public boolean remove(User user) {
        UserData.remove(user);
        return true;
    }

    // Getter and setter for current user
    public User getCurrentUser() {
        return currUser;
    }
    public void setCurrentUser(User user) {
        currUser = user;
    }

    @Override
    public String toString() {
        String str = "";
        for (User u : UserData.keySet()) {
            str += u + "\n, ";
        }
        return str;
    }

    /**
     * How we write all the users to a text file, if i'm not mistaken.
     * Read through the HashMap and add accordingly,
     * using the saveAsText command we defined in the USER class.
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
     * How do we READ FROM A file? We have a BufferedReader
     * and we clear out our HashMap. Then, we add back in again.
     * @param reader - A bufferedReader on our file.
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
