package com.example.thinh.designfilter;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.app.ActionBarDrawerToggle;

public class MainActivity extends AppCompatActivity implements LowPassFragment.OnFragmentInteractionListener,
        HightPassFragment.OnFragmentInteractionListener, BandPassFragment.OnFragmentInteractionListener,
        BandStopFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView navDrawer;
    private ActionBarDrawerToggle drawerToggle;

    /*
    * stateFilter is attribute contain values that represent each filter
    * 1: low pass filter, 2: hight pass filter, 3: band pass filter, band stop filter*/
    private int stateFilter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View headerLayout;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navDrawer = (NavigationView) findViewById(R.id.navigation_view);

        //setup drawer view ???? -> set event to navigation view
        setupDrawerContent(navDrawer);
        //  headerLayout = navDrawer.inflateHeaderView(R.layout.drawer_header);
        //  ImageView ivHeaderPhoto = headerLayout.findViewById(R.id.imageView);

        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawer.setDrawerListener(drawerToggle);
        addFragment(new LowPassFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // The action bar home/up action should open or close the drawer
        switch (id) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /*
    * set event to navigation view*/
    public void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //onOptionsItemSelected(item);
                selectDrawerItem(item);
                return false;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // open fragment after click item nav
        int id = menuItem.getItemId();
        Fragment fragment = null;
        switch (id) {
            case R.id.navFistFilter:
                fragment = new LowPassFragment();
                break;
            /*case R.id.navSecondFilter:
                fragment = new HightPassFragment();
                break;*/
            case R.id.navThirdFilter:
                fragment = new BandPassFragment();
                break;
            /*case R.id.navFourFilter:
                fragment = new BandStopFragment();
                break;*/
        }
        if (fragment != null) {
            addFragment(fragment);
        }
        mDrawer.closeDrawer(navDrawer);
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(fragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
