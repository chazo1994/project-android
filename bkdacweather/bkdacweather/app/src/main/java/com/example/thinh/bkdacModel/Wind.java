package com.example.thinh.bkdacModel;

/**
 * Created by thinh on 29/09/2015.
 */
public class Wind {
    private double speed;
    private double deg;

    public Wind(){
        speed = 0;
        deg = 0;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
