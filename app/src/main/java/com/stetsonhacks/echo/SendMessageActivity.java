package com.stetsonhacks.echo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SendMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        EditText enterMessage = (EditText)(findViewById(R.id.enterMessageText));
        TestLayoutActivity.messages.add(enterMessage.getText().toString());
    }
    public void postMessage(View view)
    {
        finish();
    }
}
