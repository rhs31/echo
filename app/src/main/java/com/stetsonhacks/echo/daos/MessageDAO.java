package com.stetsonhacks.echo.daos;

import android.location.Location;

import com.stetsonhacks.echo.models.Message;

import java.util.List;

public interface MessageDAO {
    void post(Message message);

    List<Message> getAtLocation(Location location);

}
