package com.stetsonhacks.echo.daos;

import android.location.Location;

import com.stetsonhacks.echo.models.Message;
import com.stetsonhacks.echo.utils.Callback;

import java.util.ArrayList;
import java.util.List;

public class FakeMessageDAOImpl implements MessageDAO {
    private List<Message> messages = new ArrayList<>();

    @Override
    public void post(Message message) {
        messages.add(message);
    }

    @Override
    public void getAtLocation(Location location, double radius, Callback<List<Message>> callback) {
        callback.apply(new ArrayList<>(messages));
    }
}
