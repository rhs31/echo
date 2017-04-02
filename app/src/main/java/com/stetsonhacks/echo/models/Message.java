package com.stetsonhacks.echo.models;

import android.location.Location;

public final class Message {
    private final String content;
    private final Location location;
    private final long timestamp;
    private final double radius;

    public Message(String content, Location location, long timestamp, double radius) {
        this.content = content;
        this.location = location;
        this.timestamp = timestamp;
        this.radius = radius;
    }

    public String content() {
        return content;
    }

    public Location location() {
        return location;
    }

    public long timestamp() {
        return timestamp;
    }

    public double radius() {
        return radius;
    }
}
