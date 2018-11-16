package com.example.teamsplash.donationtracker;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.LocationType;

import junit.framework.TestCase;

import static com.example.teamsplash.donationtracker.model.LocationType.DR;

/**
 * kaci's junit tests to test out location objects
 */
@RunWith(AndroidJUnit4.class)
public class kacitest extends TestCase{
    /**
     * test to see equals method works in the location class
     */
    @Test
    public void assertEqual() {
        Location w = new Location("kaci", DR, 0.00, 0.00,
                " 47 17th", "atlanta", "Georgia",
                "30363", "6780000000");
        Location x = new Location("kaci", DR, 0.00, 0.00,
                " 47 17th", "atlanta", "Georgia",
                "30363", "6780000000");
        boolean y = x.equals(w);
        assertTrue(y);
    }

    /**
     * checks to see if getname method for location works
     */
    @Test
    public void getNames(){
        Location w = new Location("kaci", DR, 0.00, 0.00,
                " 47 17th", "atlanta", "Georgia",
                "30363", "6780000000");
        Location x = new Location("kaci", DR, 0.00, 0.00,
                " 47 17th", "atlanta", "Georgia",
                "30363", "6780000000");
        String nameW = w.getName();
        String nameX = x.getName();
        assertEquals(nameW, nameX);
    }

    /**
     * checks to see if getnlocationtype method for location works
     */
    @Test
    public void getLocationTypes(){
        Location w = new Location("kaci", DR, 0.00, 0.00,
                " 47 17th", "atlanta", "Georgia",
                "30363", "6780000000");
        Location x = new Location("kaci", DR, 0.00, 0.00,
                " 47 17th", "atlanta", "Georgia",
                "30363", "6780000000");
        LocationType LTW = w.getLocationType();
        LocationType LTX = x.getLocationType();
        assertEquals(LTW, LTX);
    }
}

