package com.example.thinh.designfilter.Model.Filter;

/**
 * Created by thinh on 11/24/2015.
 */
public class IdealBandstopFilter extends IdealFilter {

    private double wc2; // wc is second symmetric frequence
    private double ws2,wp2 ; // ws is second stop frequence and wp is second pass frequence

    @Override
    public double idealFilter(int n) {
        if(n==a){
            return (1-(wc2 - wc)/(Math.PI));
        } else {
            return (-(Math.sin((n-a)*wc2) - Math.sin((n-a)*wc))/(Math.PI*(n-a)));
        }
    }

    public void caculateWc2(){
        wc2 = (ws2 + wp2)/2;
    }

    public double getWc2() {
        return wc2;
    }

    public void setWc2(double wc2) {
        this.wc2 = wc2;
    }

    public double getWs2() {
        return ws2;
    }

    public void setWs2(double ws2) {
        this.ws2 = ws2;
    }

    public double getWp2() {
        return wp2;
    }

    public void setWp2(double wp2) {
        this.wp2 = wp2;
    }
}
