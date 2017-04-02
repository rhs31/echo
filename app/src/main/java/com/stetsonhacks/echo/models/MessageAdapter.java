package com.stetsonhacks.echo.models;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stetsonhacks.echo.R;
import com.stetsonhacks.echo.activities.MapsActivity;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {
    private final Location actLocation;


    private static class ViewHolder {
        private TextView contentView;
        private TextView timeView;
        private TextView distanceView;
    }

    public MessageAdapter(Context context, int textViewResourceId, ArrayList<Message> items,
                          Location actLocation) {
        super(context, textViewResourceId, items);
        this.actLocation = actLocation;
    }

    @NonNull
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

        final Message message = getItem(position);
        if (message != null) {
            String locationStr =  "no location";
            if(message.location != null){
                final Location msgLoc = new Location("");
                msgLoc.setLongitude(message.location.longitude);
                msgLoc.setLatitude(message.location.latitude);
                locationStr = String.valueOf(((int)actLocation.distanceTo(msgLoc)) + "m away from" +
                        " you");

                viewHolder.distanceView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getContext(), MapsActivity.class);
                        i.putExtra(MapsActivity.LOCATION_EXTRA, msgLoc);
                        getContext().startActivity(i);
                    }
                });
            }

            viewHolder.contentView.setText(message.content);
            viewHolder.timeView.setText(Long.toString(message.timestamp));
            viewHolder.distanceView.setText(locationStr);


        }

        return convertView;
    }
}
