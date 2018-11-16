package com.example.teamsplash.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.LocationType;
import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.UserType;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.teamsplash.donationtracker.model.LocationType.DR;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


@SuppressWarnings("ALL")
@RunWith(AndroidJUnit4.class)
public class chrisTest extends TestCase {
    @Test
    public void userEqualsTest() {
        User a = new User("A", "A", "a@example.com", "aaaaaaaa", UserType.USER);
        assertFalse(a.equals(null));
        assertFalse(a.equals(new Object()));
        assertFalse(a.equals(new User("B", "A", "a@example.com", "aaaaaaaa", UserType.USER)));
        assertFalse(a.equals(new User("A", "B", "a@example.com", "aaaaaaaa", UserType.USER)));
        assertFalse(a.equals(new User("A", "A", "b@example.com", "aaaaaaaa", UserType.USER)));
        assertFalse(a.equals(new User("A", "A", "a@example.com", "bbbbbbbb", UserType.USER)));
        assertFalse(a.equals(new User("A", "A", "a@example.com", "aaaaaaaa", UserType.LOCATION_EMPLOYEE)));
        assertTrue(a.equals(new User("A", "A", "a@example.com", "aaaaaaaa", UserType.USER)));
        assertTrue(a.equals(a));
    }
}