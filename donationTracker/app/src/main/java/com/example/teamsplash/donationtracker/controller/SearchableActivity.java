package com.example.teamsplash.donationtracker.controller;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.ItemType;
import com.example.teamsplash.donationtracker.model.Items;
import com.example.teamsplash.donationtracker.model.Location;

import java.util.ArrayList;
import java.util.List;


/*
    Class that is the Searchable Activity. Using Chris Obando's code from Location Fragment
    as reference.

    We essentially have to take in a query from a search, and run it through two methods -
    one that checks if the query is actually an Enum string (in which case we get all items
    that match the Enum string) or if the query is an item name, and gets all
    items with that item name. We of course add these items to an ArrayList, which we then
    have to display as a ListView.
 */
public class SearchableActivity extends ListActivity {
    ListView listItems;
    SearchAdapter listAdapter;
    ArrayList<Item> searchList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.search_layout); // UI page for results.
        
        //Get the intent, verify the action, and get the query.
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchList = new ArrayList<>();
            searchList = doMySearch(query, searchList);
        }
    }

    /*
    Actual creating view method. I use an inflater, container, and bundle
    to initialize the vertical list, and then set a Listener so that when I click
    on the item, it puts the details into the next page (which is hopefully ItemDetail).
    */

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.search_layout, container, false);
        listAdapter = new SearchAdapter(inflater, searchList);
        listItems = fragment.findViewById(R.id.inventory);
        listItems.setAdapter(listAdapter);

        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item itemClicked = searchList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ITEM", itemClicked);
                Intent listDetails = new Intent(SearchableActivity.this, ItemDetail.class);
                listDetails.putExtras(bundle);
                startActivity(listDetails);
            }
        });

        return fragment;
    }



    /*
        Actual searching activity. DoMySearch invokes helper methods based
        on whether or not the string is an Enum or is actually a name of a thing.
        It's easier to check if the thing is an enum or not, so that is the method invoked
        first.
     */
    private ArrayList<Item> doMySearch(String query, ArrayList<Item> searchList) {
        Items instance = Items.getInstance(); // gets singleton instance of Items.
        for (ItemType type: ItemType.values()) { // checking for enum.
            if (query.equals(type.getType())) {
                return enumSearch(searchList, query, type, instance); // invoke enumSearch
            }
        }
        return nameSearch(searchList, query, instance);

    }

    /*
        enumSearch function. Will return a list.
        @param searchList - an ArrayList we'll be modifying via pass-by-reference.
        @param query - a String from the search.
        @param type - an ItemType that represents the matched enum
        @param instance - a singleton instance of Items whose ItemData arrayList we'll be iterating over.

     */
    private ArrayList<Item> enumSearch(ArrayList<Item> searchList, String query, ItemType type, Items instance) {
        for (Item eachItem: instance.getItemData()) {
            if (eachItem.getItemType().getType().equals(type.getType())) {
                searchList.add(eachItem);
            }
        }
        return searchList;
    }

    private ArrayList<Item> nameSearch(ArrayList<Item> searchList, String query, Items instance) {
        for (Item eachItem: instance.getItemData()) {
            if (eachItem.getDesc().equals(query)) {
                searchList.add(eachItem);
            }
        }
        return searchList;
    }



    /*
        Following Chris Obando's code from Location Fragment class. I create
        a class that allows us to instantiate an Adapter. I then use this
        Adapter in conjunction with my ArrayList and other xml files
        to create a vertical list of items based on either type of list.
     */

    private class SearchAdapter extends ArrayAdapter<Item> {
        private final LayoutInflater inflater;
        private final ArrayList<Item> itemList;

        public SearchAdapter(LayoutInflater inflater, ArrayList<Item> itemList) {
            super(inflater.getContext(), R.layout.item_fragment, itemList);
            this.inflater = inflater;
            this.itemList = itemList;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            Item item = itemList.get(position);
            View rowView= inflater.inflate(R.layout.item_fragment, null, true);
            TextView name = rowView.findViewById(R.id.name);
            TextView value = rowView.findViewById(R.id.value);
            name.setText(item.getDesc());
            value.setText("$" + String.format( "%.2f", item.getValue()));
            return rowView;
        }

    }

}
