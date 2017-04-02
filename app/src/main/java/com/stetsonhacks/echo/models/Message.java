package com.stetsonhacks.echo.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public final class Message {
    public String content;
    public GeoLocation location;
    public long timestamp;
    public double radius;

    public Message() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Message(String content, GeoLocation location, long timestamp, double radius) {
        this.content = content;
        this.location = location;
        this.timestamp = timestamp;
        this.radius = radius;
    }
}
