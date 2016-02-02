package com.example.thinh.designfilter.Model.Filter;

/**
 * Created by thinh on 11/24/2015.
 */
public class IdealHightpassFilter extends IdealFilter {
    @Override
    public double idealFilter(int n) {
        if(n == a){
            return (1-wc/(Math.PI));
        } else {
            return (-(Math.sin(wc*(n-a)))/(Math.PI*(n-a)));
        }
    }

    private double delta(int n){
        if(n==0){
            return 1;
        } else {
            return 0;
        }
    }
}
