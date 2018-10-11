package com.example.teamsplash.donationtracker.model;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserType usertype;

    public User(String firstname, String lastname, String email, String password, UserType usertype) {
        this.firstname = firstname;
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
    private String getFirstName() {
        return firstname;
    }

    private void setFirstName(String name) {
        this.firstname = name;
    }

    // Getter and setter for last name
    private String getLastName() {
        return lastname;
    }

    private void setLastName(String name) {
        this.lastname = name;
    }

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

    @Override
    public String toString() {
        return (email + ": " + password);
    }
}