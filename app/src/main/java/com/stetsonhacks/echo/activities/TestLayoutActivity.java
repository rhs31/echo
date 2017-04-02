package com.stetsonhacks.echo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

    }


    public void onResume() {
        super.onResume();  // Always call the superclass method first

        Log.d(TAG, "----------------------location : " + getLastKnownLocation());

        if (getLastKnownLocation() == null) {
            Toast.makeText(this, "You have to enable the GPS for this app to work.", Toast
                    .LENGTH_LONG);
            findViewById(R.id.floatingActionButton).setEnabled(false);
        } else {
            findViewById(R.id.floatingActionButton).setEnabled(true);
            MessageDAOFactory.get().getAtLocation(getLastKnownLocation(), new Callback<List<Message>>() {
                @Override
                public void apply(List<Message> messages) {
                    ListView messageListView = (ListView) findViewById(R.id.messageListView);
                    final ArrayList<String> messageStrings = new ArrayList<String>();
                    for (int i = 0; i < messages.size(); i++) {
                        messageStrings.add(messages.get(i).content);
                    }
                    ArrayAdapter<Message> arrayAdapter = new MessageAdapter(TestLayoutActivity.this,
                            android.R.id.text1, (ArrayList) messages, getLastKnownLocation());

                    messageListView.setAdapter(arrayAdapter);

                    messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                            Toast.makeText(getApplicationContext(), messageStrings.get(i),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }
}

