package com.example.teamsplash.donationtracker.model;

import java.util.HashMap;

public class Users {

    // TODO add error messaging letting users know why different things may have failed

    private static final Users _instance = new Users();
    public static Users getInstance() {
        return _instance;
    }

    public static HashMap<User, String> UserData;
    private User currUser;

    private Users() {
        UserData = new HashMap<User, String>();
        // Hardcode sample user for test login convenience
        UserData.put(new User("First", "Last", "user", "password", UserType.USER), "password");
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
            if (u.getPassword().equals(password) && u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public boolean contains(User user) {
        return UserData.containsKey(user);
    }

    public boolean contains(String email, String password) {
        return (get(email, password) != null);
    }

    public boolean remove(User user) {
        UserData.remove(user);
        return true;
    }

    // Getter and setter for current user
    // TODO : not sure what the functionality of this is?
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

}
