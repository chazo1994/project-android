package com.example.thinh.bkdacModel;

/**
 * Created by thinh on 29/09/2015.
 */
public class Main {
    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double sea_level;
    private double grnd_level;
    private int humidity;
    private double temp_kf;

    public Main(){
        temp = 0;
        temp_min = 0;
        temp_max = 0;
        pressure = 0;
        sea_level = 0;
        grnd_level = 0;
        humidity = 0;
        temp_kf = 0;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSea_level() {
        return sea_level;
    }

    public void setSea_level(double sea_level) {
        this.sea_level = sea_level;
    }

    public double getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(double grnd_level) {
        this.grnd_level = grnd_level;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(double temp_kf) {
        this.temp_kf = temp_kf;
    }
}
