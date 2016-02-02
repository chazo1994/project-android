package com.example.thinh.bkdacweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddLocation extends AppCompatActivity {
    private EditText etLocal;
    private Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        etLocal = (EditText) findViewById(R.id.etLocal);
        btAdd = (Button) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLocation.this,MainWeatherActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("localCity", etLocal.getText().toString());
                intent.putExtra("myLocal",bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_location, menu);
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
