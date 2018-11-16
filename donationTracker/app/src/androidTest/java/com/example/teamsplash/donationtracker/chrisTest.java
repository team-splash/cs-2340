package com.example.teamsplash.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.UserType;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;


@SuppressWarnings("ALL")
@RunWith(AndroidJUnit4.class)
public class chrisTest extends TestCase {
    @Test
    public void userEqualsTest() {
        final User a = new User("A", "A", "a@example.com", "aaaaaaaa", UserType.USER);
        assertNotEquals(null, a);
        assertNotEquals(new Object(), a);
        assertNotEquals(new User("B", "A", "a@example.com", "aaaaaaaa", UserType.USER), a);
        assertNotEquals(new User("A", "B", "a@example.com", "aaaaaaaa", UserType.USER), a);
        assertNotEquals(new User("A", "A", "b@example.com", "aaaaaaaa", UserType.USER), a);
        assertNotEquals(new User("A", "A", "a@example.com", "bbbbbbbb", UserType.USER), a);
        assertNotEquals(new User("A", "A", "a@example.com", "aaaaaaaa", UserType.LOCATION_EMPLOYEE), a);
        assertEquals(new User("A", "A", "a@example.com", "aaaaaaaa", UserType.USER), a);
        assertEquals(a, a);
    }
}