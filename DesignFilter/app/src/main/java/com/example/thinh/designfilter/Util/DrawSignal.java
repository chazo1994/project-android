package com.example.thinh.designfilter.Util;

import android.graphics.Color;
import android.graphics.Paint;
import android.widget.LinearLayout;

import com.example.thinh.designfilter.DrawFilterActivity;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.CombinedXYChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.ScatterChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by thinh on 11/22/2015.
 * class manage drawable Chart on Activity
 */
public class DrawSignal {
    private GraphicalView myChart;
    private XYSeriesRenderer signalRenderer;
    private XYSeries signalSeries;
    private XYMultipleSeriesRenderer mySeriesRender;
    private XYMultipleSeriesDataset mySeriesDataset;
    private LinearLayout llChart;
    private DrawFilterActivity drawFilterActivity;

    public DrawSignal(DrawFilterActivity drawFilterActivity, LinearLayout llChart ){
        this.llChart = llChart;
        this.drawFilterActivity = drawFilterActivity;
        initChart();
    }

    /*
   * initialize all configuration to Chart
   * */
    public void initChart(){
        signalRenderer = new XYSeriesRenderer();

        mySeriesRender = new XYMultipleSeriesRenderer();
        mySeriesDataset = new XYMultipleSeriesDataset();

        //creat signal and filter that you want draw
        signalSeries = new XYSeries("pulse");

        // set XYSeriesRender to render data on screen 
        signalRenderer.setColor(Color.BLUE);
        signalRenderer.setDisplayBoundingPoints(true);
        signalRenderer.setPointStyle(PointStyle.CIRCLE);
        signalRenderer.setPointStrokeWidth(1);

        
        //set Render Control full chart (control: color, axist, background , line ...)

        mySeriesRender.addSeriesRenderer(signalRenderer);
/*
        mySeriesRender.addSeriesRenderer(filterRenderer);
*/
        mySeriesRender.setPanEnabled(false, false);
        //mySeriesRender.setYAxisMax(0.5);
        //mySeriesRender.setYAxisMin(0);
        mySeriesRender.setShowGrid(true);
        mySeriesRender.setGridColor(Color.BLACK);
        mySeriesRender.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margin
        mySeriesRender.setXLabels(10);
        mySeriesRender.setYLabels(10);
        mySeriesRender.setXLabelsColor(Color.BLUE);
        mySeriesRender.setYLabelsColor(0, Color.BLUE);
        mySeriesRender.setAxisTitleTextSize(5);
        mySeriesRender.addXTextLabel(mySeriesRender.getXAxisMax(), "n");
        mySeriesRender.addYTextLabel(mySeriesRender.getYAxisMax(), "h[n]");
        //mySeriesRender.setPointSize(2);


        // add data signal and filter to dataset
        mySeriesDataset.addSeries(signalSeries);
        /*mySeriesDataset.addSeries(filterSeries);*/
    }

    /*
    * Add data to XYSeries
    * */

    public void addData(double[] filters,int M){

        for(int i = 0; i<M;i++){
            signalSeries.add(i-1/M,0);
            signalSeries.add(i,filters[i]);
            signalSeries.add(i+1/M,0);
        }
        int k= 0;
    }

    public void DrawChart(double[] filters, int M){

        initChart();
        addData(filters,M);
        CombinedXYChart.XYCombinedChartDef[] types = { new CombinedXYChart.XYCombinedChartDef(LineChart.TYPE, 0),
                new CombinedXYChart.XYCombinedChartDef(ScatterChart.TYPE, 0)};
        GraphicalView gvChart = ChartFactory.getCombinedXYChartView(drawFilterActivity, mySeriesDataset, mySeriesRender,types);
        //GraphicalView gvChart = ChartFactory.getLineChartView(drawFilterActivity, mySeriesDataset, mySeriesRender);
        llChart.addView(gvChart);
    }

    public void DrawFrequency(double[] am, int numberPoint){

        mySeriesRender.setPointSize(0);
        mySeriesRender.setBarWidth(2);
        for(int i = 0; i<=numberPoint;i++){
            signalSeries.add((Math.PI)*i/numberPoint,am[i]);
        }
        GraphicalView gvChart = ChartFactory.getLineChartView(drawFilterActivity, mySeriesDataset, mySeriesRender);
        llChart.addView(gvChart);
    }

    public void drawApliputeForTestSine(double wc1, double wc2){
        mySeriesRender.setPointSize(4);
        signalSeries.add(0,0);
        signalSeries.add(wc1,0);
        signalSeries.add(wc1,1);
        signalSeries.add(wc1+0.01,1);
        signalSeries.add(wc1+0.01,0);
        signalSeries.add(wc2,0);
        signalSeries.add(wc2,1);
        signalSeries.add(wc2+0.01,1);
        signalSeries.add(wc2+0.01,0);
        signalSeries.add(Math.PI,0);
        GraphicalView gvChart = ChartFactory.getLineChartView(drawFilterActivity, mySeriesDataset, mySeriesRender);
        llChart.addView(gvChart);
    }


}
