package com.example.teamsplash.donationtracker.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.model.Item;
import com.example.teamsplash.donationtracker.R;

import java.util.List;

@SuppressWarnings("unused but needed in order to run")
class ItemListAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Item> mItemList;

    /**
     * @param mContext a context item
     * @param mItemList list of items
     */
    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
    public ItemListAdapter(Context mContext, List<Item> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;
    }


    /**
     * @return the count of items in list
     */
    @Override
    public int getCount() {
        return mItemList.size();
    }

    /**
     * @param position the area where it's at
     * @return object whatever poisition of the item
     */
    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    /**
     * @param position location in list
     * @return long number of return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * @param position where in list
     * @param convertView view item of item
     * @param parent a viewgroup item
     * @return view the view we want
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            //noinspection this helps with null cases of convert view
            View v = View.inflate(mContext, R.layout.item_fragment, null);
        }
        View v = View.inflate(mContext, R.layout.item_fragment, null);
        TextView name = v.findViewById(R.id.name);
        TextView price = v.findViewById(R.id.value);

        //Set text for TextView
        name.setText(mItemList.get(position).getDesc());
        price.setText(String.format("$%s", String.valueOf(mItemList.get(position).getValue())));

        return v;
    }
}
