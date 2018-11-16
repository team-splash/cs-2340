package com.example.teamsplash.donationtracker;
import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.LocationType;


import org.junit.Test;
import org.junit.runner.RunWith;



import junit.framework.TestCase;


import static com.example.teamsplash.donationtracker.model.LocationType.DR;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


@SuppressWarnings("ALL")
@RunWith(AndroidJUnit4.class)
public class chrisTest extends TestCase {
    @Test
    public void LocationsTypeFromString(){
        Location newLoc = new Location("loc", DR, 1.23, 1.23,
                "123", "123", "123", "12345", "1234567890");
        LocationType type = newLoc.getLocationType();
        LocationType x = LocationType.fromString(type.toString());
        assertNotNull(x);
    }
    @Test
    public void getTypeMethod() {
        Location newLoc = new Location("loc", DR, 1.23, 1.23,
                "123", "123", "123", "12345", "1234567890");
        LocationType type = newLoc.getLocationType();
        String theType = type.getType();
        assertThat(theType, containsString("Drop Off"));
    }
    @Test
    public void getTypeToString() {
        Location newLoc = new Location("loc", DR, 1.23, 1.23,
                "123", "123", "123", "12345", "1234567890");
        LocationType type = newLoc.getLocationType();
        String theType = type.toString();
        assertNotNull(theType);
    }
}