package com.example.teamsplash.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.model.Items;
import com.example.teamsplash.donationtracker.model.Location;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * item made into a fragment so that the bottom part of the app can be accessed
 */
@SuppressWarnings("unused")
public class ItemFragment extends Fragment {

    /**
     * @param inflater the laout inflated
     * @param container where the fragment is contained
     * @param savedInstanceState  what is used for the button
     * @return View this is the view we get after
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.items_fragment, container, false);

        Location location = (Location) Objects.requireNonNull(
                getArguments()).getSerializable("LOCATION");

        final List<Item> itemsList = Items.getInstance().getByLocation(location);

        ListAdapter listAdapter = new ItemsList(inflater, itemsList);
        final ListView list = fragment.findViewById(R.id.inventory);
        list.setAdapter(listAdapter);
        setListViewHeightBasedOnItems(list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * @param parent the parent adapterview
             * @param view the view we have
             * @param position where it's located
             * @param id the id of the item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item itemClicked = itemsList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("ITEM", itemClicked);
                Intent listDetails = new Intent(ItemFragment.this.getActivity(), ItemDetail.class);
                listDetails.putExtras(bundle);

                startActivity(listDetails);
            }
        });

        return fragment;
    }

    private class ItemsList extends ArrayAdapter<Item> {

        private final LayoutInflater inflater;
        private final List<Item> inventory;

        ItemsList(LayoutInflater inflater, List<Item> inventory) {
            super(inflater.getContext(), R.layout.item_fragment, inventory);
            this.inflater = inflater;
            this.inventory = inventory;
        }

        /**
         * @param position where the location is plaed
         * @param view the view frame that shows it all
         * @param parent the view group before
         * @return View the view we want
         */
        @SuppressWarnings("unused")
        @NonNull
        @Override
        public View getView(int position, View view, @NonNull ViewGroup parent) {
            Item item = inventory.get(position);
            if (view ==null){
                //noinspection helps with possible cases of null views(was an issue with lint)
                View rowView = inflater.inflate(R.layout.item_fragment, parent, false);
            }
            View rowView = inflater.inflate(R.layout.item_fragment, parent, false);
            TextView name = rowView.findViewById(R.id.name);
            TextView value = rowView.findViewById(R.id.value);

            name.setText(item.getDesc());
            value.setText(String.format("$%s", String.format(Locale.US, "%.2f",
                    item.getValue())));
            return rowView;
        }
    }

    private static void setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int numberOfItems = listAdapter.getCount();
            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }
            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();
        }

    }
}
