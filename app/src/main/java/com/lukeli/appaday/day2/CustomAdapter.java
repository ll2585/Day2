package com.lukeli.appaday.day2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {
    public CustomAdapter(Context context, String[] values) {
        super(context, R.layout.row_layout, values);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater custom_inflater = LayoutInflater.from(getContext());

        View custom_view = custom_inflater.inflate(R.layout.row_layout, parent, false);

        String friday = getItem(position);

        TextView custom_textView = (TextView) custom_view.findViewById(R.id.row_layout_textView);

        custom_textView.setText(friday);

        return custom_view;
    }
}
