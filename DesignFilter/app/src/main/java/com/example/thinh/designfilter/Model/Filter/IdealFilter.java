package com.example.thinh.designfilter.Model.Filter;

/**
 * Created by thinh on 11/23/2015.
 */
public abstract class IdealFilter {

    private int M;

    protected int a; // a is symmetric point
    protected double wc; // wc is fist symmetric frequence
    protected double ws, wp; // ws is first stop frequence and wp is first pass frequence

    public abstract double idealFilter(int n);

    public void caculateA() {
        if (getM() != 0) {
            a = (this.getM() - 1) / 2;
        }
    }

    public void caculateWc() {
        wc = (ws + wp) / 2;
    }

    public void setM(int M) {
        this.M = M;
    }

    public int getM(){
        return M;
    }
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getWc() {
        return wc;
    }

    public void setWc(double wc) {
        this.wc = wc;
    }

    public double getWs() {
        return ws;
    }

    public void setWs(double ws) {
        this.ws = ws;
    }

    public double getWp() {
        return wp;
    }

    public void setWp(double wp) {
        this.wp = wp;
    }
}
