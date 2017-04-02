package com.stetsonhacks.echo.models;


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class GeoLocation implements Serializable {
    public double longitude;
    public double latitude;

    public GeoLocation(){}

    public GeoLocation(double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
