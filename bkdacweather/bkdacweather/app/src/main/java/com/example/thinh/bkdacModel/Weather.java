package com.example.thinh.bkdacModel;

import java.util.List;

/**
 * Created by thinh on 29/09/2015.
 */
public class Weather {
    private long id;
    private String main;
    private String description;
    private String icon;

    public Weather(){
        id = 0;
        main = "";
        description = "";
        icon = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
