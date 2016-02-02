package com.example.thinh.bkdacModel;

/**
 * Created by thinh on 30/09/2015.
 */
public class City {
    private long id;
    private String name;
    private Coord coord;
    private String country;
    private long population;
    private SysCity sys;

    public City(){
        id = 0;
        name = "";
        coord = new Coord();
        country = "";
        population = 0;
        sys = new SysCity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public SysCity getSys() {
        return sys;
    }

    public void setSys(SysCity sys) {
        this.sys = sys;
    }
}
