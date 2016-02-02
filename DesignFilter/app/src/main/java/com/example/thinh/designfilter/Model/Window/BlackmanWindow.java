package com.example.thinh.designfilter.Model.Window;

/**
 * Created by thinh on 11/23/2015.
 */
public class BlackmanWindow extends Window{
    @Override
    public double getWindow(int n) {
        if((n >= 0) && (n <= (M-1))){

            return (0.42 - 0.5*Math.cos((double)(2*n)*Math.PI/((double)(M-1))) + 0.08*Math.cos((double)(4*n)*Math.PI/((double)(M-1))));
        } else {

            return 0;
        }
    }
}
