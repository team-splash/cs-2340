package com.example.teamsplash.donationtracker.model;

public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private UserType type;

    private static String capitalize(final String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    User(final String firstName, final String lastName, final String emailAddress, final UserType type) {
        this.firstName = capitalize(firstName);
        this.lastName = capitalize(lastName);
        this.emailAddress = emailAddress;
        this.type = type;
    }

    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof User))
            return false;

        final User anotherUser = (User) anObject;

        if (firstName == null) {
            if (anotherUser.firstName != null)
                return false;
        } else {
            if (!(firstName.equals(anotherUser.firstName)))
                return false;
        }

        if (lastName == null) {
            if (anotherUser.lastName != null)
                return false;
        } else {
            if (!(lastName.equals(anotherUser.lastName)))
                return false;
        }

        if (emailAddress == null) {
            if (anotherUser.emailAddress != null)
                return false;
        } else {
            if (!(emailAddress.equals(anotherUser.emailAddress)))
                return false;
        }

        if (type != anotherUser.type)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (firstName == null ? 0 : firstName.hashCode());
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + (emailAddress == null ? 0 : emailAddress.hashCode());
        result = 31 * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getLastName() + ", " + getFirstName() + " <" + getEmailAddress() + ">";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserType getType() {
        return type;
    }

    public void setType(final UserType type) {
        this.type = type;
    }
}
