package com.example.teamsplash.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.User;
import com.example.teamsplash.donationtracker.model.UserType;
import com.example.teamsplash.donationtracker.model.Users;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;


@SuppressWarnings("ALL")
@RunWith(AndroidJUnit4.class)
public class matthewTest extends TestCase {
    @Test
    public void testUsersAdd() {
        User a = new User("A", "A", "a@example.com", "aaaaaaaa", UserType.USER);
        User b = new User("B", "A", "a@example.com", "bbbbbbbb", UserType.USER);
        Users.getInstance().add(a);
        assertTrue(Users.getInstance().contains("a@example.com", "aaaaaaaa"));
        assertEquals(a, Users.getInstance().get("a@example.com", "aaaaaaaa"));
        assertFalse(Users.getInstance().contains("a@xample.com", "bbbbbbbb"));
        Users.getInstance().add(b);
        assertTrue(Users.getInstance().contains("a@example.com", "aaaaaaaa"));
        assertEquals(a, Users.getInstance().get("a@example.com", "aaaaaaaa"));
        assertFalse(Users.getInstance().contains("a@xample.com", "bbbbbbbb"));
    }
}