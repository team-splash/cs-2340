package com.example.teamsplash.donationtracker;
import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.User;


import org.junit.Test;
import org.junit.runner.RunWith;



import junit.framework.TestCase;


import static com.example.teamsplash.donationtracker.model.UserType.MANAGER;
import static com.example.teamsplash.donationtracker.model.UserType.USER;
import static org.junit.Assert.assertNotEquals;


@RunWith(AndroidJUnit4.class)
public class sarahTest extends TestCase {
    @Test
    public void equals() {
        User one = new User("sarah", "branham", "sarah@gmail.com", "password", MANAGER);
        User two = new User("chris", "obando", "chris@yahoo.com", "password", MANAGER);
        boolean nos = one.equals(two);

        assertNotEquals(nos, true);
    }
    @Test
    public void names() {
        User sarah = new User("sarah", "branham", "sarah@gmail.com", "password", USER);
        String sarahName = sarah.getName();
        assertNotNull(sarahName);
    }
    @Test
    public void emailCheck() {
        User sarah = new User("sarah", "branham", "sarah@gmail.com", "password", USER);
        String email = sarah.getEmail();
        assertEquals("sarah@gmail.com", email);
    }
    
}