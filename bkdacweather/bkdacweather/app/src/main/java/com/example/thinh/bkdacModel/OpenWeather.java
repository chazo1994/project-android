package com.example.thinh.bkdacModel;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by thinh on 29/09/2015.
 */
public class OpenWeather {
    private City city;
    private int cod;
    private double message;
    private int cnt;
    private List<WeatherDay> list;
    public OpenWeather(){
        city = new City();
        cod = 0;
        message = 0;
        cnt = 0;
        list = new ArrayList<WeatherDay>();
        for(int i=0;i<5;i++){
            list.add(new WeatherDay());
        }
        int i=0;
        i=i+1;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherDay> getList() {
        return list;
    }

    public void setList(List<WeatherDay> list) {
        this.list = list;
    }
}
