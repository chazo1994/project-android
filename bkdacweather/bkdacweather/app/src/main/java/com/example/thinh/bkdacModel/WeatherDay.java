package com.example.thinh.bkdacModel;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by thinh on 04/10/2015.
 */
public class WeatherDay {
    private long dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private Sys sys;
    private String dt_txt;

    public WeatherDay(){
        dt = 0;
        main = new Main();
        Weather wt = new Weather();
        weather = new ArrayList<Weather>();
        weather.add(new Weather());
        clouds = new Clouds();
        wind = new Wind();
        sys = new Sys();
        dt_txt = "";
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
