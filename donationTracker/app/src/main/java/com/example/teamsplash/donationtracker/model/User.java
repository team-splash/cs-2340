package com.example.teamsplash.donationtracker.model;
import com.example.teamsplash.donationtracker.model.UserType;

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

        return (((User) o).getFirstname().equals(this.firstname)
                && ((User) o).getLastname().equals(this.lastname)
                && ((User) o).getEmail().equals(this.email)
                && ((User) o).getPassword().equals(this.getPassword())
                && ((User) o).usertype.equals(this.usertype));

    }

    private String getFirstname() {
        return firstname;
    }
    private void setFirstname(String name) { firstname = name;}

    private String getLastname() {
        return lastname;
    }
    private void setLastname(String name) { lastname = name;}

    public String getEmail() {return email;}
    public void setEmail(String address) {email = address;}

    public String getPassword() {return password;}
    public void setPassword(String updated) {password = updated;}

    public UserType getUsertype() {
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
