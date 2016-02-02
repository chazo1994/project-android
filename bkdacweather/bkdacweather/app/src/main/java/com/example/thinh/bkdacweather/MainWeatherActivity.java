package com.example.thinh.bkdacweather;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.thinh.bkdacUtils.WeatherAsyncTask;

public class MainWeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_weather);
        Intent calledIntent = getIntent();
        Bundle reciveBundle = calledIntent.getBundleExtra("myLocal");
        String myCity = reciveBundle.get("localCity").toString();
        WeatherAsyncTask weatherAsyncTask = new WeatherAsyncTask(this, myCity);
        weatherAsyncTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        switch(id) {
            case R.id.itemAbout:
                intent = new Intent(this,About.class);
                startActivity(intent);
                break;
            case R.id.itemSetting:
                intent = new Intent(this,Setting.class);
                startActivity(intent);
                break;
            case R.id.itemAdd:
                intent = new Intent(this,AddLocation.class);
                startActivity(intent);
                break;
            case R.id.itemAboutMe:
                intent = new Intent(this,AboutMe.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
