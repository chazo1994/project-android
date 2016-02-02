package com.example.thinh.designfilter.Model.Window;

/**
 * Created by thinh on 11/23/2015.
 */
public class HammingWindow extends Window {
    @Override
    public double getWindow(int n) {
        if((n >= 0) && (n <= (M-1))){

            return (0.54 - 0.46*Math.cos((double)(2*n)*Math.PI/((double)(M-1))));
        } else {

            return 0;
        }
    }
}
