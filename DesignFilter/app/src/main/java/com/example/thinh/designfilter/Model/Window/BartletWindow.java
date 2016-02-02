package com.example.thinh.designfilter.Model.Window;

/**
 * Created by thinh on 11/23/2015.
 */
public class BartletWindow extends Window {
    @Override
    public double getWindow(int n) {
        if((n>0) && (n<(int)((M-1)/2))){

            return ((2*n)/(M-1));
        } else if((n>(int)((M-1)/2)) && (n<(M-1))) {

            return (2 - ((2*n)/(M-1)));
        } else {

            return 0;
        }
    }
}
