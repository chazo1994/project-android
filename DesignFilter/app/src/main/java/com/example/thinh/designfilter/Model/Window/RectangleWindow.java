package com.example.thinh.designfilter.Model.Window;

/**
 * Created by thinh on 11/23/2015.
 */
public class RectangleWindow extends Window {
    @Override
    public double getWindow(int n) {
        if((n >= 0) && (n <= (M-1))){

            return 1;
        } else {

            return 0;
        }
    }
}
