package com.stetsonhacks.echo;

import android.location.Location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.stetsonhacks.echo.daos.MessageDAO;
import com.stetsonhacks.echo.daos.MessageDAOFactory;
import com.stetsonhacks.echo.models.Message;

public class SendMessageActivity extends AppCompatActivity {
    private static final String TAG = "SendMessageActivity";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        findViewById(R.id.postMessageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enterMessage = (EditText)(findViewById(R.id.enterMessageText));
                Message m = new Message(enterMessage.getText().toString(), null, 0, 1);
                MessageDAO mdao = MessageDAOFactory.get();
                mdao.post(m);
                Log.d(TAG, "post !");
                finish();
            }
        });

    }
    public void cancel(View view)
    {
        finish();
    }

}
