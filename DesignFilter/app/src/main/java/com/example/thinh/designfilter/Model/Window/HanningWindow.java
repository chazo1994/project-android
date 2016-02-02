package com.example.thinh.designfilter.Model.Window;

/**
 * Created by thinh on 11/23/2015.
 */
public class HanningWindow extends Window {
    @Override
    public double getWindow(int n) {
        if((n >= 0) && (n <= (M-1))){

            return (0.5 - 0.5*Math.cos((double)(2*n)*Math.PI/((double)(M-1))));
        } else {

            return 0;
        }
    }
}
