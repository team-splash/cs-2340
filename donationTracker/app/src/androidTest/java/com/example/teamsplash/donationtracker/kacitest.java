package com.example.teamsplash.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.LocationType;
import com.example.teamsplash.donationtracker.model.Locations;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

/**
 * kaci's junit tests to test out location objects
 */
@RunWith(AndroidJUnit4.class)
public class kacitest extends TestCase{
    /**
     * tests the names of locations
     */
    @Test
    public void testLocationsGetNames() {
        Location a = new Location("A", LocationType.DR, 0.0, 0.0, "A", "A", "MA", "01001",
                "0000000000");
        Location b = new Location("B", LocationType.DR, 0.0, 0.0, "B", "B", "MA", "01001",
                "0000000001");
        String[] emptyLocationNames = {};
        String[] aLocationNames = {"A"};
        String[] abLocationNames = {"A", "B"};
        assertEquals(Arrays.asList(emptyLocationNames), Locations.getInstance().getNames());
        Locations.getInstance().add(a);
        assertEquals(Arrays.asList(aLocationNames), Locations.getInstance().getNames());
        Locations.getInstance().add(b);
        assertEquals(Arrays.asList(abLocationNames), Locations.getInstance().getNames());
    }
}

