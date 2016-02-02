package com.example.thinh.bkdacweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_me, menu);
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
        };

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
