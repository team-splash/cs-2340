package com.example.teamsplash.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.Location;
import com.example.teamsplash.donationtracker.model.User;


import org.junit.Test;
import org.junit.runner.RunWith;



import junit.framework.TestCase;




import static com.example.teamsplash.donationtracker.model.ItemType.CLO;

import static com.example.teamsplash.donationtracker.model.LocationType.WA;

import static com.example.teamsplash.donationtracker.model.UserType.USER;


@SuppressWarnings("ALL")
@RunWith(AndroidJUnit4.class)
public class matthewTest extends TestCase{
    @Test
    public void userTypeCheck() {
        User matthew = new User("Matthew", "Marting",
                "mmarting1@outlook.com", "password123!", USER);
        assertEquals(matthew.getUserType().toString(), USER.toString());
    }
    @Test
    public void locationTypeCheck() {
        Location warehouse = new Location("warehouse", WA, -10.00,
                100.00, "Address", "city", "state",
                "100000", "1234567890");
        assertEquals(warehouse.getLocationType().toString(), WA.toString());
    }
    @Test
    public void itemTypeCheck() {
        Location warehouse = new Location("warehouse", WA, -10.00, 100.00,
                "Address", "city", "state",
                "100000", "1234567890");
        Item sweater = new Item("15:30", warehouse, "sweater",
                "knitted", 30.00, CLO);
        assertEquals(sweater.getItemType().toString(), CLO.toString());
    }
}