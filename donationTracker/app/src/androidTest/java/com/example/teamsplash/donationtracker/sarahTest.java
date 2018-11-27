package com.example.teamsplash.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.LocationType;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;


@SuppressWarnings("ALL")
@RunWith(AndroidJUnit4.class)
public class sarahTest extends TestCase {
    @Test
    public void testLocationEquals() {
        Location a = new Location("A", LocationType.DR, 0.0, 0.0, "A", "A", "MA", "01001", "0000000000");
        assertNotEquals(null, a);
        assertNotEquals(new Object(), a);
        assertNotEquals(new Location("B", LocationType.DR, 0.0, 0.0, "A", "A", "MA", "01001", "0000000000"), a);
        assertNotEquals(new Location("A", LocationType.ST, 0.0, 0.0, "A", "A", "MA", "01001", "0000000000"), a);
        assertNotEquals(new Location("A", LocationType.DR, 1.0, 0.0, "A", "A", "MA", "01001", "0000000000"), a);
        assertNotEquals(new Location("A", LocationType.DR, 0.0, 1.0, "A", "A", "MA", "01001", "0000000000"), a);
        assertNotEquals(new Location("A", LocationType.DR, 0.0, 0.0, "B", "A", "MA", "01001", "0000000000"), a);
        assertNotEquals(new Location("A", LocationType.DR, 0.0, 0.0, "A", "B", "MA", "01001", "0000000000"), a);
        assertNotEquals(new Location("A", LocationType.DR, 0.0, 0.0, "A", "A", "RI", "01001", "0000000000"), a);
        assertNotEquals(new Location("A", LocationType.DR, 0.0, 0.0, "A", "A", "MA", "02804", "0000000000"), a);
        assertNotEquals(new Location("A", LocationType.DR, 0.0, 0.0, "A", "A", "MA", "01001", "0000000001"), a);
        assertEquals(new Location("A", LocationType.DR, 0.0, 0.0, "A", "A", "MA", "01001", "0000000000"), a);
        assertEquals(a, a);
    }
}