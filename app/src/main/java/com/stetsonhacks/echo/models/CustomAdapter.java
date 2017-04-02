package com.stetsonhacks.echo.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stetsonhacks.echo.R;

import java.util.ArrayList;

/**
 * Created by rsilv on 4/2/2017.
 */

public class CustomAdapter extends ArrayAdapter<Message> {

    private static class ViewHolder {
        private TextView contentView;
        private TextView timeView;
        private TextView distanceView;
    }

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Message> items) {
        super(context, textViewResourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.message_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.contentView = (TextView) convertView.findViewById(R.id.content);
            viewHolder.timeView = (TextView) convertView.findViewById(R.id.time);
            viewHolder.distanceView = (TextView) convertView.findViewById(R.id.distance);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.contentView.setText(item.content());
            viewHolder.timeView.setText(Long.toString(item.timestamp()));
            viewHolder.distanceView.setText("Stetson University");
        }

        return convertView;
    }
}
