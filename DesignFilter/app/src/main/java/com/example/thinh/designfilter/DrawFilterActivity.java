package com.example.thinh.designfilter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.thinh.designfilter.Dao.Filter;
import com.example.thinh.designfilter.Util.DrawSignal;
import com.example.thinh.designfilter.Util.ViewPagerAdapter;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class DrawFilterActivity extends AppCompatActivity {


    private Bundle bundle;
    private Filter filter;
    private ProgressDialog progressDialog=null;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter vAdapter;
    private CharSequence titles[] = {"Bộ lọc","Tín hiệu kiểm tra"};
    private SlidingTabLayout tabs;
    int numOftabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_filter);

        progressDialog = ProgressDialog.show(this,"Please wait","processing excute...");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vAdapter = new ViewPagerAdapter(getSupportFragmentManager(),titles,numOftabs);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(vAdapter);

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer(){
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        tabs.setViewPager(viewPager);

        Intent callerIntent = getIntent();
        bundle = callerIntent.getBundleExtra("informFilter");

        drawFilter();
        vAdapter.setFilter(filter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog.hide();
        progressDialog.dismiss();
    }

    public void drawFilter() {
        Intent callerIntent = getIntent();

        bundle = callerIntent.getBundleExtra("informLowpassFilter");
        if (bundle != null) {
            filter = new Filter(bundle.getDouble("passFrequence1")*Math.PI, bundle.getDouble("stopFrequence1")*Math.PI);
            filter.setDeltaP(bundle.getDouble("deltaPass"));
            filter.setDeltaS(bundle.getDouble("deltaStop"));
            filter.filterLowpass();
            getSupportActionBar().setTitle(getResources().getString(R.string.title1));
            // drawSignal.DrawChart(filter.getFilters(),filter.getM());

            return;
        }

        bundle = callerIntent.getBundleExtra("informHightpassFilter");
        if (bundle != null) {
            filter = new Filter(bundle.getDouble("passFrequence1")*Math.PI, bundle.getDouble("stopFrequence1")*Math.PI);
            filter.setDeltaP(bundle.getDouble("deltaPass"));
            filter.setDeltaS(bundle.getDouble("deltaStop"));
            filter.filterHightpass();
            //drawSignal.DrawChart(filter.getFilters(),filter.getM());

            return;
        }

        bundle = callerIntent.getBundleExtra("informBandpassFilter");
        if (bundle != null) {
            filter = new Filter(bundle.getDouble("passFrequence1")*Math.PI, bundle.getDouble("stopFrequence1")*Math.PI, bundle.getDouble("passFrequence2")*Math.PI, bundle.getDouble("stopFrequence2")*Math.PI);
            filter.setDeltaP(bundle.getDouble("deltaPass"));
            filter.setDeltaS(bundle.getDouble("deltaStop"));
            filter.filterBandpass();
            //  drawSignal.DrawChart(filter.getFilters(),filter.getM());

            return;
        }

        bundle = callerIntent.getBundleExtra("informBandstopFilter");
        if (bundle != null) {
            filter = new Filter(bundle.getDouble("passFrequence1")*Math.PI, bundle.getDouble("stopFrequence1")*Math.PI, bundle.getDouble("passFrequence2")*Math.PI, bundle.getDouble("stopFrequence2")*Math.PI);
            filter.setDeltaP(bundle.getDouble("deltaPass"));
            filter.setDeltaS(bundle.getDouble("deltaStop"));
            filter.filterBandstop();
            // drawSignal.DrawChart(filter.getFilters(),filter.getM());

            return;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_draw_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

    }
}
