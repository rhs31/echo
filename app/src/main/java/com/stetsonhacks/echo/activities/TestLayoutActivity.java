package com.stetsonhacks.echo.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stetsonhacks.echo.R;
import com.stetsonhacks.echo.daos.MessageDAOFactory;
import com.stetsonhacks.echo.models.MessageAdapter;
import com.stetsonhacks.echo.models.Message;
import com.stetsonhacks.echo.utils.Callback;

import java.util.ArrayList;
import java.util.List;


public class TestLayoutActivity extends WithLocationActivity {
    private static final String TAG = "TestLayoutActivity";

    public void writeMessage(View view) {
        Intent myIntent = new Intent(TestLayoutActivity.this, SendMessageActivity.class);
        TestLayoutActivity.this.startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layout);
        final TextView radiusText = (TextView)(findViewById(R.id.radiusText));
        final SeekBar radiusBar = (SeekBar)findViewById(R.id.radiusSeekBar);

        radiusBar.setMax(100);
        radiusBar.setProgress(50);
        radiusBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int min = 1;
            int radius;
            if (progress < min)
            {
                radius = min;
                radiusBar.setProgress(min);

            }
            else
            {
                radius = progress;
            }
            radiusText.setText(Integer.toString(radius));


    }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            ;
            int radius = 10;
            ArrayList<String>timesTableContent = new ArrayList<String>();

        });}

    public void onResume(){
        super.onResume();  // Always call the superclass method first

        Log.d(TAG, "----------------------location : " + getLastKnownLocation());

        if (getLastKnownLocation() == null) {
            Toast.makeText(this, "You have to enable the GPS for this app to work.", Toast
                    .LENGTH_LONG).show();
            findViewById(R.id.floatingActionButton).setEnabled(false);
        } else {
            findViewById(R.id.floatingActionButton).setEnabled(true);
            MessageDAOFactory.get().getAtLocation(getLastKnownLocation(), 70, new
                    Callback<List<Message>>() {
                        @Override
                        public void apply(final List<Message> messages) {
                            ListView messageListView = (ListView) findViewById(R.id.messageListView);
                            ArrayAdapter<Message> arrayAdapter = new MessageAdapter(TestLayoutActivity.this,
                                    android.R.id.text1, (ArrayList) messages, getLastKnownLocation());

                            messageListView.setAdapter(arrayAdapter);

                            messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                                    Intent intent = new Intent(TestLayoutActivity.this, MapsActivity.class);
                                    Message m = messages.get(i);
                                    Location msgLoc = new Location("");
                                    msgLoc.setLongitude(m.location.longitude);
                                    msgLoc.setLatitude(m.location.latitude);
                                    intent.putExtra(MapsActivity.LOCATION_EXTRA, msgLoc);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
        }

    }
}

