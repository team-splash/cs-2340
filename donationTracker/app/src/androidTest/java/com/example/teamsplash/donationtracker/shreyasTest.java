package com.example.teamsplash.donationtracker;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.teamsplash.donationtracker.model.Item;

import com.example.teamsplash.donationtracker.model.Location;

import static org.junit.Assert.assertNotEquals;

import junit.framework.TestCase;

import static com.example.teamsplash.donationtracker.model.ItemType.CLO;
import static com.example.teamsplash.donationtracker.model.LocationType.DR;

@RunWith(AndroidJUnit4.class)
public class shreyasTest extends TestCase {
    @Test
    public void equalsMethod() {
        Location a = new Location("location", DR, 0.00, 0.00, "0", "0", "0", "00000", "0000000000");
        Location b = new Location("location", DR, 1.00, 1.00, "1", "1", "1", "10000", "0000000000");
        Item realItem = new Item("12:00", a, "item", "some item", 0.00, CLO);
        Item compareItem = new Item("2:00", b, "items", "something", 1.00, CLO);
        boolean reals = realItem.equals(compareItem);
        assertNotEquals(reals, false);
    }
    @Test
    public void gettingTime(){
        Location a = new Location("location", DR, 0.00, 0.00, "0", "0", "0", "00000", "0000000000");
        Item realItem = new Item("12:00", a, "item", "some item", 0.00, CLO);
        Item compareItem = new Item("12:00", a, "item", "some item", 0.00, CLO);
        String realTime = realItem.getTime();
        String compareTime = compareItem.getTime();
        assertEquals(realTime,compareTime);
    }
    @Test
    public void parsing(){
        Location a = new Location("location", DR, 0.00, 0.00, "0", "0", "0", "00000", "0000000000");
        Item realItem = new Item("12:00", a, "item", "some item", 0.00, CLO);
        String myString = realItem.toString();
        assertNotNull(myString);
    }

}