package com.titanium.easymemory.entity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {
    private Context mContext;

    public PersonAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return choices.size();
    }

    public Object getItem(int position) {
        if (position >= choices.size()) return null;
        return choices.get(position);
    }

    public long getItemId(int position) {
        if (position >= choices.size()) return -1;
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView person = new TextView(mContext);
        person.setText(choices.get(position).name);
        return person;
    }

    private ArrayList<FriendList.Friend> choices;
    public void addQuestion(FriendList.Friend p) {
        this.choices.add(p);
    }
}
