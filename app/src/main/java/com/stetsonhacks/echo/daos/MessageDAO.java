package com.stetsonhacks.echo.daos;

import android.location.Location;

import com.stetsonhacks.echo.models.Message;
import com.stetsonhacks.echo.utils.Callback;

import java.util.List;

public interface MessageDAO {
    void post(Message message);

    void getAtLocation(Location location, double radius, Callback<List<Message>> callback);

}
