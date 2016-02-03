package com.example.thinh.ghis;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import CustomAdapter.TabPagerAdapter;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener,Listutorial.OnFragmentInteractionListener,MainContent.OnFragmentInteractionListener,LearnHistory.OnFragmentInteractionListener {
    private String itemLv[] = {"gameLs","hocLs"};
    private String titleTabs[];
    private ListView lvMenuDrawer ;
    private DrawerLayout dlMenuDrawer ;
    private ActionBarDrawerToggle drawerToggle;
    private android.support.v7.app.ActionBar actionBar;
    private ActionBar.LayoutParams layoutParams;
    private ViewPager viewPager;
    private TabPagerAdapter tabPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dlMenuDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        lvMenuDrawer = (ListView) findViewById(R.id.lvDrawer);
        actionBar = getSupportActionBar();
        layoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View customActiobar = LayoutInflater.from(this).inflate(R.layout.custome_action_bar,null);

        actionBar.setCustomView(customActiobar, layoutParams);
        actionBar.setDisplayShowCustomEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,dlMenuDrawer, R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };

        //set swipe tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.tabspager);
        viewPager.setAdapter(tabPagerAdapter);
        titleTabs = getResources().getStringArray(R.array.titleTabs);
        for(String tabname : titleTabs){
            android.support.v7.app.ActionBar.Tab tab = actionBar.newTab();
            tab.setTabListener(this);
            tab.setText(tabname);
            actionBar.addTab(tab);
        }

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
       // actionBar.
        ArrayAdapter<String> adapterLv = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemLv);
        lvMenuDrawer.setAdapter(adapterLv);
        lvMenuDrawer.setOnItemClickListener(new DrawerClickItemListener());

        drawerToggle.setDrawerIndicatorEnabled(true);
        dlMenuDrawer.setDrawerListener(drawerToggle);
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

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // This method should always be called by your Activity's
        // onConfigurationChanged method.
        drawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    private class DrawerClickItemListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        public void selectItem(int position){
            Fragment fragment = new Listutorial();
            Bundle bundle = new Bundle();

            bundle.putInt("position", position);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_Frame, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(fragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
            dlMenuDrawer.closeDrawer(lvMenuDrawer);

        }
    }
}
