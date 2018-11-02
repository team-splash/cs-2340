package com.example.teamsplash.donationtracker.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.teamsplash.donationtracker.R;
import com.example.teamsplash.donationtracker.model.Item;

import java.util.List;

public class ItemListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Item> mItemList;

    public ItemListAdapter(Context mContext, List<Item> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_fragment, null);
        TextView name = (TextView)v.findViewById(R.id.name);
        TextView price = (TextView)v.findViewById(R.id.value);

        //Set text for TextView
        name.setText(mItemList.get(position).getDesc());
        price.setText("$" + String.valueOf(mItemList.get(position).getValue()));

        return v;
    }
}
