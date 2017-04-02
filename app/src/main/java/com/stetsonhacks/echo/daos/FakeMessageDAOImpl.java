package com.stetsonhacks.echo.daos;

import android.location.Location;

import com.stetsonhacks.echo.models.Message;

import java.util.ArrayList;
import java.util.List;

public class FakeMessageDAOImpl implements MessageDAO {
    private List<Message> messages = new ArrayList<>();

    @Override
    public void post(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> getAtLocation(Location location) {
        return new ArrayList<>(messages);
    }
}
