package com.stetsonhacks.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class TestLayoutActivity extends AppCompatActivity {

    public static ArrayList<String> messages = new ArrayList<String>(asList("Test message dfasdf sadf asdf asdf sadf asdfas df adsf adsf asdfas d f as", "Turn Left", "Hi", "ayy lmao"));

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
        ListView messageListView = (ListView) findViewById(R.id.messageListView);

        //final ArrayList<String> messages = new ArrayList<String>(asList("Test message dfasdf sadf asdf asdf sadf asdfas df adsf adsf asdfas d f as", "Turn Left", "Hi", "ayy lmao"));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);

        messageListView.setAdapter(arrayAdapter);

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(getApplicationContext(), messages.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

