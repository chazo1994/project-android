package com.example.thinh.designfilter.Util;

/**
 * Created by thinh on 11/28/2015.
 */
public class TestSignal {
    private double wc1, wc2;
    public static int MAXPOIT = 100;

    public TestSignal(double wc1, double wc2){
        this.wc1 = wc1;
        this.wc2 = wc2;
    }

    public double signalTest(int n){
        return (Math.cos(n*wc1)+Math.cos(n*wc2));
    }
    public double[] drawTest(){
        double[] temp = new double[MAXPOIT];
        for (int i=0;i<MAXPOIT;i++){
            temp[i] = signalTest(i);
        }
        return temp;
    }

   /* public double frequencyResponse(double w){
        double r = 0;
        if((w == wc1) || (w == -wc1) || (w == wc2) || (w == -wc2)){
            r = Math.PI;
        }
        return r;
    }*/

    public double Amplipute(double w){
        double re = 0, im = 0;
        for(int i=0;i<MAXPOIT;i++){
            re = re + signalTest(i)*(Math.cos(w*i));
            im = im - signalTest(i)*(Math.sin(w*i));
        }
        return Math.sqrt(re*re + im*im);
    }
    public double[] drawAmplipute(){
        double[] temp = new double[MAXPOIT+1];
        for (int i=0;i<=MAXPOIT;i++){
            double w = Math.PI*i/MAXPOIT;
            temp[i] = Amplipute(w);
        }
        /*for (int i=0;i<=1;i++){
            double w = Math.PI*i/MAXPOIT;
            temp[i] = 1;
        }*/
        return temp;
    }

    public double getWc1() {
        return wc1;
    }

    public void setWc1(double wc1) {
        this.wc1 = wc1;
    }

    public double getWc2() {
        return wc2;
    }

    public void setWc2(double wc2) {
        this.wc2 = wc2;
    }

    public static int getMAXPOIT() {
        return MAXPOIT;
    }

    public static void setMAXPOIT(int MAXPOIT) {
        TestSignal.MAXPOIT = MAXPOIT;
    }
}
