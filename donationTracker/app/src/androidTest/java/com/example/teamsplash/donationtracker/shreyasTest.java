package com.example.teamsplash.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.ItemType;
import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.LocationType;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

/**
 * test for Shreyas Casturi, checking certain Location methods.
 *
 */
@RunWith(AndroidJUnit4.class)
public class shreyasTest extends TestCase {
    /**
     * test to see if the item equal method works
     */
    @Test
    public void testItemEquals() {
        Location location = new Location("A", LocationType.DR, 0.0, 0.0, "A", "A", "MA", "01001",
                "0000000000");
        Item a = new Item("00:00:00", location, "A", "a", 0.0, ItemType.CLO);
        assertNotEquals(null, a);
        assertNotEquals(new Object(), a);
        assertNotEquals(new Item("00:00:01", location, "A", "a", 0.0, ItemType.CLO), a);
        assertNotEquals(new Item("00:00:00", null, "A", "a", 0.0, ItemType.CLO), a);
        assertNotEquals(new Item("00:00:00", location, "B", "a", 0.0, ItemType.CLO), a);
        assertNotEquals(new Item("00:00:00", location, "A", "b", 0.0, ItemType.CLO), a);
        assertNotEquals(new Item("00:00:00", location, "A", "a", 1.0, ItemType.CLO), a);
        assertNotEquals(new Item("00:00:00", location, "A", "a", 0.0, ItemType.ELE), a);
        assertEquals(new Item("00:00:00", location, "A", "a", 0.0, ItemType.CLO), a);
        assertEquals(a, a);
    }
}