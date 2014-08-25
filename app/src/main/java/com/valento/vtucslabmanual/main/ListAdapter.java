package com.valento.vtucslabmanual.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.valento.vtucslabmanual.helper.Helper;

import java.util.ArrayList;

import blink9.com.vtucslabmanual.R;

class ListAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> items;

    public ListAdapter(Context context, int resource, ArrayList<String> items) {
        super(context, resource);
        items = new ArrayList<String>();
        this.context = context;
        this.items = items;
    }

    @Override
    public void add(String object) {
        items.add(object);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row, parent, false);
        TextView t = (TextView) row.findViewById(R.id.singleRowtextView);
        t.setText(Helper.removeExtention(items.get(position)));
        return row;
    }
}
