package com.example.thinh.designfilter.Model.Window;

/**
 * Created by thinh on 11/23/2015.
 */
public abstract  class Window {
    protected int M =0; // number poit cut of result signal

    /*
    * return output of rectagle window*/
    public abstract double getWindow(int n);

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }
}
