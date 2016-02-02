package com.example.thinh.designfilter.Model.Filter;

/**
 * Created by thinh on 11/23/2015.
 */
public class IdealLowpassFilter extends IdealFilter {


    @Override
    public double idealFilter(int n) {
        if(n == a){
            return (wc/(Math.PI));
        }
        return ((Math.sin(wc*(n-a)))/(Math.PI*(n-a)));
    }

}
