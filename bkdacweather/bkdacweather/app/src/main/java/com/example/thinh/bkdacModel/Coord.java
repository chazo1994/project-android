package com.example.thinh.bkdacModel;

/**
 * Created by thinh on 04/10/2015.
 */
public class Coord {
    private double lon;
    private double lat;

    public Coord(){
        lon = 0;
        lat = 0;
    }

    public double getLon() {
        return lon;

    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
