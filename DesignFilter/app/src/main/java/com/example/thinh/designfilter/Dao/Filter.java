package com.example.thinh.designfilter.Dao;

import com.example.thinh.designfilter.Model.Filter.IdealBandpassFilter;
import com.example.thinh.designfilter.Model.Filter.IdealBandstopFilter;
import com.example.thinh.designfilter.Model.Filter.IdealFilter;
import com.example.thinh.designfilter.Model.Filter.IdealHightpassFilter;
import com.example.thinh.designfilter.Model.Filter.IdealLowpassFilter;
import com.example.thinh.designfilter.Model.Window.BartletWindow;
import com.example.thinh.designfilter.Model.Window.BlackmanWindow;
import com.example.thinh.designfilter.Model.Window.HammingWindow;
import com.example.thinh.designfilter.Model.Window.HanningWindow;
import com.example.thinh.designfilter.Model.Window.RectangleWindow;
import com.example.thinh.designfilter.Model.Window.Window;
import com.example.thinh.designfilter.Util.TestSignal;

/**
 * Created by thinh on 11/22/2015.
 */
public class Filter {
    private double constA[] = {21,25,44,53,74};
    /*
    * attribute chooseWindow represent window choosed
    * if chooseWindow = 1 then rectangle window is choosed014
    * if chooseWindow = 2 then bartlettWindow window is choosed
    * if chooseWindow = 3 then hanningWindow window is choosed
    * if chooseWindow = 4 then hammingWindow window is choosed
    * if chooseWindow = 5 then blackmanWindow window is choosed*/
    private int chooseWindow = 1;
    private double deltaW = 1.8;
    private int M = 0;
    private double ws1,ws2,wp1,wp2;

    private IdealLowpassFilter idealLowpassFilter;
    private IdealHightpassFilter idealHightpassFilter;
    private IdealBandpassFilter idealBandpassFilter;
    private IdealBandstopFilter idealBandstopFilter;
    private double filters[];
    private double output[];
    private Window window;
    private TestSignal testSignal;

    private double deltaS = 0.01, deltaP, As;

    public Filter(){
        init(0,0);
    }

    /*
    * initialize low pass filter or hight pass filter*/
    public Filter(double wp1, double ws1){
        init(wp1,ws1);
        this.ws1 = ws1;
        this.wp1 = wp1;
    }

    /*
    * initialize band pass filter or band stop filter*/
    public Filter(double wp1, double ws1, double wp2, double ws2){
        init(wp1,ws1);
        this.ws1 =ws1;
        this.ws2 = ws2;
        this.wp1 = wp1;
        this.wp2 = wp2;
    }

    public void init(double w1, double w2){
        window = new RectangleWindow();
        idealLowpassFilter = new IdealLowpassFilter();
        idealHightpassFilter = new IdealHightpassFilter();
        idealBandpassFilter = new IdealBandpassFilter();
        idealBandstopFilter = new IdealBandstopFilter();

        double wtest1,wtest2;

        if(w1<w2){
            wtest2 = (Math.PI + w2)/2;
            wtest1 = w1/2;
        } else {
            wtest2 = (Math.PI - w1)/2;
            wtest1 = w2/2;
        }
        TestSignal testSignal = new TestSignal(wtest1,wtest2);
    }

    public double[] filterLowpass(){
        checkWindow();
      //  M = (int) ((Math.PI*deltaW)/(Math.abs(idealLowpassFilter.getWs() - idealLowpassFilter.getWp()))+1);
        M = (int) ((Math.PI*deltaW)/(Math.abs(ws1 - wp1))+1);
        idealLowpassFilter.setM(M);
        idealLowpassFilter.setWs(ws1);
        idealLowpassFilter.setWp(wp1);
        idealLowpassFilter.caculateA();
        idealLowpassFilter.caculateWc();
        filters = new double[M];

        window.setM(M);
        for(int i=0 ;i<M;i++){
            filters[i] = idealLowpassFilter.idealFilter(i)*window.getWindow(i);
        }
        return filters;
    }

    public double[] filterHightpass(){
        checkWindow();
      //  M = (int) ((Math.PI*deltaW)/(Math.abs(idealHightpassFilter.getWs() - idealHightpassFilter.getWp()))+1);
        M = (int) ((Math.PI*deltaW)/(Math.abs(wp1 - ws1))+1);
        idealHightpassFilter.setM(M);
        idealHightpassFilter.setWs(ws1);
        idealHightpassFilter.setWp(wp1);
        idealHightpassFilter.caculateA();
        idealHightpassFilter.caculateWc();
        filters = new double[M];

        window.setM(M);
        for(int i=0 ;i<M;i++){
            filters[i] = idealHightpassFilter.idealFilter(i)*window.getWindow(i);
        }
        return filters;
    }

    public double[] filterBandpass(){
        checkWindow();
        //  M = (int) ((Math.PI*deltaW)/(Math.abs(idealHightpassFilter.getWs() - idealHightpassFilter.getWp()))+1);
        if((wp1-ws1)<(ws2-wp2)){
            M = (int) ((Math.PI*deltaW)/(Math.abs(wp1-ws1))+1);
        } else {
            M = (int) ((Math.PI*deltaW)/(Math.abs(ws2-wp2))+1);
        }
        idealBandpassFilter.setM(M);
        idealBandpassFilter.setWs(ws1);
        idealBandpassFilter.setWp(wp2);
        idealBandpassFilter.setWs2(ws2);
        idealBandpassFilter.setWp2(wp2);
        idealBandpassFilter.caculateA();
        idealBandpassFilter.caculateWc2();
        idealBandpassFilter.caculateWc();
        filters = new double[M];

        window.setM(M);
        for(int i=0 ;i<M;i++){
            filters[i] = idealBandpassFilter.idealFilter(i)*window.getWindow(i);
        }
        return filters;
    }

    public double[] filterBandstop(){
        checkWindow();
        //  M = (int) ((Math.PI*deltaW)/(Math.abs(idealHightpassFilter.getWs() - idealHightpassFilter.getWp()))+1);
        if((ws1-wp1)<(wp2-ws2)){
            M = (int) ((Math.PI*deltaW)/(Math.abs(wp1-ws1))+1);
        } else {
            M = (int) ((Math.PI*deltaW)/(Math.abs(ws2-wp2))+1);
        }
        idealBandstopFilter.setM(M);
        idealBandstopFilter.setWs(ws1);
        idealBandstopFilter.setWp(wp1);
        idealBandstopFilter.setWs2(ws2);
        idealBandstopFilter.setWp2(wp2);
        idealBandstopFilter.caculateA();
        idealBandstopFilter.caculateWc2();
        idealBandstopFilter.caculateWc();
        filters = new double[M];

        window.setM(M);
        for(int i=0 ;i<M;i++){
            filters[i] = idealBandstopFilter.idealFilter(i)*window.getWindow(i);
        }
        return filters;
    }
    /*
    * this function will Initialize window that is choosed
    * */
    public void checkWindow(){
        chooseWindow();
        switch (chooseWindow){
            case 1:
                window = new RectangleWindow();
                deltaW = 1.8;
                break;
            case 2:
                window = new BartletWindow();
                deltaW = 6.1;
                break;
            case 3:
                window = new HanningWindow();
                deltaW = 6.2;
                break;
            case 4:
                window = new HammingWindow();
                deltaW = 6.6;
                break;
            case 5:
                window = new BlackmanWindow();
                deltaW = 11;
                break;
        }
    }

    /*
    * this function set attribute chooseWindow correspond!*/
    public void chooseWindow(){
        As = 20*Math.log10(deltaS);
        double temp = Math.abs(As), Maxd = constA[4];
        for(int i=0;i<5;i++){
            double d = Math.abs(temp - constA[i]);
            if(d < Maxd){
                Maxd = d;
                chooseWindow = i+1;
            }
        }
    }

    public double phasefrequencyResponse(double w){
        double fq = 0;
        double r = 0, im = 0;
        for(int i=0; i<M;i++){
            r = r + filters[i]*Math.cos(w*i);
            im = im - filters[i]*Math.sin(w*i);
        }
        fq = Math.atan(im/r);
        return fq;
    }

    public double[] drawPhase(){
        double ps[] = new double[M+1];

        for(int i=0; i<=M;i++) {
            double w =Math.PI*i/M;
           // ps[i] =20*Math.log10(phasefrequencyResponse(w));
            ps[i] = phasefrequencyResponse(w);
        }

        return ps;
    }

    public double AmpliputeResponse(double w){
        double fr = 0;
        double r = 0, im = 0;
        for(int i=0; i<M;i++){
            r = r + filters[i]*Math.cos(w*i);
            im = im - filters[i]*Math.sin(w*i);
        }
        fr = Math.sqrt((r*r + im*im));
        r = 0;
        im = 0;
        return fr;
    }

    /*
    * draw M+1 point between 0 to pi of amplipute */
    public double[] drawAmplipute(){
        double af[] = new double[M+1];

        for(int i=0; i<=M;i++) {
            double w =Math.PI*i/M;
            af[i] =AmpliputeResponse(w);
        }

        return af;
    }

    public double[] drawGAmplipute(){
        double af[] = new double[M+1];

        for(int i=0; i<=M;i++) {
            double w =Math.PI*i/M;
            af[i] =20*Math.log10(AmpliputeResponse(w)/AmpliputeResponse(0));
        }

        return af;
    }
    public double outputTestSignal(int n){
        double out = 0;
        for (int k = 0; k < M; k++) {
            out = out + filters[k] * testSignal.signalTest(n - k);
        }
        return out;
    }

    public double[] drawOutput(){
        double[] temp = new double[TestSignal.MAXPOIT+1];
        for (int i=0;i<=TestSignal.MAXPOIT;i++){
            temp[i] = outputTestSignal(i);
        }
        return temp;
    }

    /*
    * draw 100 point between 0 to 2*pi of amplipute Test*/
    public double[] drawAmpliputeTest(){
        double af[] = new double[TestSignal.MAXPOIT+1];

        for(int i=0; i<=TestSignal.MAXPOIT;i++) {
            double w = i* Math.PI /TestSignal.MAXPOIT;
            af[i] = ampliputeFrequencyOutput(w);
        }

        return af;
    }
    public double ampliputeFrequencyOutput(double w){
            double re = 0, im = 0;
            for(int k = 0; k<M ;k++){
                re = re + outputTestSignal(k)*(Math.cos(k*w));
                im = im - outputTestSignal(k)*(Math.sin(k*w));
            }

        return  Math.sqrt(re*re+im*im);
    }

    public int getChooseWindow() {
        return chooseWindow;
    }

    public void setChooseWindow(int chooseWindow) {
        this.chooseWindow = chooseWindow;
    }

    public IdealLowpassFilter getIdealLowpassFilter() {
        return idealLowpassFilter;
    }

    public void setIdealLowpassFilter(IdealLowpassFilter idealLowpassFilter) {
        this.idealLowpassFilter = idealLowpassFilter;
    }

    public IdealHightpassFilter getIdealHightpassFilter() {
        return idealHightpassFilter;
    }

    public void setIdealHightpassFilter(IdealHightpassFilter idealHightpassFilter) {
        this.idealHightpassFilter = idealHightpassFilter;
    }

    public IdealBandpassFilter getIdealBandpassFilter() {
        return idealBandpassFilter;
    }

    public void setIdealBandpassFilter(IdealBandpassFilter idealBandpassFilter) {
        this.idealBandpassFilter = idealBandpassFilter;
    }

    public IdealBandstopFilter getIdealBandstopFilter() {
        return idealBandstopFilter;
    }

    public void setIdealBandstopFilter(IdealBandstopFilter idealBandstopFilter) {
        this.idealBandstopFilter = idealBandstopFilter;
    }

    public double[] getFilters() {
        return filters;
    }

    public void setFilters(double[] filters) {
        this.filters = filters;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public double getDeltaS() {
        return deltaS;
    }

    public void setDeltaS(double deltaS) {
        this.deltaS = deltaS;
    }

    public double getDeltaP() {
        return deltaP;
    }

    public void setDeltaP(double deltaP) {
        this.deltaP = deltaP;
    }

    public double[] getOutput() {
        return output;
    }

    public void setOutput(double[] output) {
        this.output = output;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public double getWs1() {
        return ws1;
    }

    public void setWs1(double ws1) {
        this.ws1 = ws1;
    }

    public double getWp1() {
        return wp1;
    }

    public void setWp1(double wp1) {
        this.wp1 = wp1;
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

    public TestSignal getTestSignal() {
        return testSignal;
    }

    public void setTestSignal(TestSignal testSignal) {
        this.testSignal = testSignal;
    }
}
