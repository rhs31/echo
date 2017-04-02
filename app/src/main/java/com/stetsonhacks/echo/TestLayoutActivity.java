package com.stetsonhacks.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.stetsonhacks.echo.daos.FakeMessageDAOImpl;
import com.stetsonhacks.echo.daos.MessageDAO;
import com.stetsonhacks.echo.daos.MessageDAOFactory;
import com.stetsonhacks.echo.models.Message;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.message;
import static java.util.Arrays.asList;

public class TestLayoutActivity extends AppCompatActivity {

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

        List<Message> messages = MessageDAOFactory.get().getAtLocation(null);
        final ArrayList<String> messageStrings = new ArrayList<String>();
        for (int i = 0; i < messages.size(); i++)
        {
            messageStrings.add(messages.get(i).content());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messageStrings);

        messageListView.setAdapter(arrayAdapter);

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(getApplicationContext(), messageStrings.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

