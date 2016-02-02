package com.example.thinh.bkdacUtils;

import com.example.thinh.bkdacModel.OpenWeather;
import com.example.thinh.bkdacweather.R; // class di vcc eo soi thay ms uc che chu

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.DialogPreference;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

//import com.example.thinh.bkdacweather.R;
/**
 * Created by thinh on 30/09/2015.
 */
public class WeatherAsyncTask extends AsyncTask<Void,Void,OpenWeather> {

    private Activity activity;
    private ProgressDialog progressDialog;
    private String local;
    private Bitmap sunBitmap = null;
    private Bitmap sunBitmap1 = null;
    private Bitmap sunBitmap2 = null;
    private Bitmap sunBitmap3 = null;
    private Bitmap sunBitmap4 = null;
    private int resBackground;
    private boolean check;

    public  WeatherAsyncTask(Activity activity,String local){
        this.activity = activity;
        this.local = local;
        this.progressDialog = new ProgressDialog(activity);
        this.progressDialog.setTitle("Nguyễn Văn Thịnh");
        this.progressDialog.setMessage("Đang tải thông tin...");
        this.progressDialog.setCancelable(true);
    }
    @Override
    protected  void onPreExecute(){
        super.onPreExecute();
        this.progressDialog.show();
    }
    @Override
    protected OpenWeather doInBackground(Void... params) {

        OpenWeather openWeather=null;
        resBackground = R.drawable.bp32;
        try {
            openWeather = OpenWeatherAPI.loadJson(local);
            String idIcon = openWeather.getList().get(0).getWeather().get(0).getIcon().toString();
            String idIcon1 = openWeather.getList().get(1).getWeather().get(0).getIcon().toString();
            String idIcon2 = openWeather.getList().get(2).getWeather().get(0).getIcon().toString();
            String idIcon3 = openWeather.getList().get(3).getWeather().get(0).getIcon().toString();
            String idIcon4 = openWeather.getList().get(4).getWeather().get(0).getIcon().toString();

            sunBitmap = loadSunImage(idIcon);
            sunBitmap1 = loadSunImage(idIcon1);
            sunBitmap2 = loadSunImage(idIcon2);
            sunBitmap3 = loadSunImage(idIcon3);
            sunBitmap4 = loadSunImage(idIcon4);
            resBackground = loadBackground(openWeather.getCity().getName());
        } catch(Exception e){

            openWeather = new OpenWeather();
            check = true;
            this.progressDialog.dismiss();

        }


        return openWeather;
    }
    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPostExecute(OpenWeather openWeather) {
        if(isCheck()){
            OpenDialogMessage();
            Toast toast = Toast.makeText(activity,"ERROR: CAN'T CONNECT INTERNET OR FOUND LOCATION!",Toast.LENGTH_LONG);
            toast.show();
            int k=0;
        }
        ImageView imgBackground = (ImageView) activity.findViewById(R.id.imgBackground);
        //day0
        TextView tvDay0 = (TextView) activity.findViewById(R.id.tvDay0);
        TextView tvTemp = (TextView) activity.findViewById(R.id.tvTemp);
        TextView tvTempMaxMin = (TextView) activity.findViewById(R.id.tvTempMaxMin);
        TextView tvLocation = (TextView) activity.findViewById(R.id.tvLocation);
        TextView tvMain = (TextView) activity.findViewById(R.id.tvMain);
        TextView tvWind = (TextView) activity.findViewById(R.id.tvWind);
        ImageView imgSun0 = (ImageView) activity.findViewById(R.id.imgsun0);

        //day1
        TextView tvDay1 = (TextView) activity.findViewById(R.id.tvDay1);
        ImageView imgsun1 = (ImageView) activity.findViewById(R.id.imgsun1);
        TextView tvTem1 = (TextView) activity.findViewById(R.id.tvTem1);
        TextView tvMain1 = (TextView) activity.findViewById(R.id.tvMain1);

        // day 2
        TextView tvDay2 = (TextView) activity.findViewById(R.id.tvDay2);
        ImageView imgsun2 = (ImageView) activity.findViewById(R.id.imgsun2);
        TextView tvTem2 = (TextView) activity.findViewById(R.id.tvTem2);
        TextView tvMain2 = (TextView) activity.findViewById(R.id.tvMain2);

        //day3
        TextView tvDay3 = (TextView) activity.findViewById(R.id.tvDay3);
        ImageView imgsun3 = (ImageView) activity.findViewById(R.id.imgsun3);
        TextView tvTem3 = (TextView) activity.findViewById(R.id.tvTem3);
        TextView tvMain3 = (TextView) activity.findViewById(R.id.tvMain3);

        //day4
        TextView tvDay4 = (TextView) activity.findViewById(R.id.tvDay4);
        ImageView imgsun4 = (ImageView) activity.findViewById(R.id.imgsun4);
        TextView tvTem4 = (TextView) activity.findViewById(R.id.tvTem4);
        TextView tvMain4 = (TextView) activity.findViewById(R.id.tvMain4);

        imgBackground.setImageResource(resBackground);

        //update day0
        tvDay0.setText(openWeather.getList().get(0).getDt_txt());
        tvTemp.setText(subStringTemp(openWeather.getList().get(0).getMain().getTemp()+"",2)+"°C");
        tvTempMaxMin.setText(subStringTemp(""+openWeather.getList().get(0).getMain().getTemp_max(),2)+"°C/"+subStringTemp(""+openWeather.getList().get(0).getMain().getTemp_min(),2)+"°C");
        tvLocation.setText(openWeather.getCity().getName() + "," + openWeather.getCity().getCountry());
        imgSun0.setImageBitmap(sunBitmap);
        tvMain.setText(tvMain.getText() + openWeather.getList().get(0).getWeather().get(0).getDescription());
        tvWind.setText(tvWind.getText()+String.valueOf(openWeather.getList().get(0).getWind().getSpeed()));

        try {
            //update day1
            Date day1 = convertStringToDate(openWeather.getList().get(1).getDt_txt());
            tvDay1.setText(day1.getHours() +"h00");
            imgsun1.setImageBitmap(sunBitmap1);
            tvTem1.setText(subStringTemp(""+openWeather.getList().get(1).getMain().getTemp_max(),2)+"°C/"+subStringTemp(""+openWeather.getList().get(1).getMain().getTemp_min(),2)+"°C");
            tvMain1.setText(openWeather.getList().get(1).getWeather().get(0).getDescription());

            //update day 2
            Date day2 = convertStringToDate(openWeather.getList().get(2).getDt_txt());
            tvDay2.setText(day2.getHours() +"h00");
            imgsun2.setImageBitmap(sunBitmap2);
            tvTem2.setText(subStringTemp(""+openWeather.getList().get(2).getMain().getTemp_max(),2)+"°C/"+subStringTemp(""+openWeather.getList().get(2).getMain().getTemp_min(),2)+"°C");
            tvMain2.setText(openWeather.getList().get(2).getWeather().get(0).getDescription());

            //update day 3
            Date day3 = convertStringToDate(openWeather.getList().get(3).getDt_txt());
            tvDay3.setText(day3.getHours() +"h00");
            imgsun3.setImageBitmap(sunBitmap3);
            tvTem3.setText(subStringTemp(""+openWeather.getList().get(3).getMain().getTemp_max(),2)+"°C/"+subStringTemp(""+openWeather.getList().get(3).getMain().getTemp_min(),2)+"°C");
            tvMain3.setText(openWeather.getList().get(3).getWeather().get(0).getDescription());

            //update day 4
            Date day4 = convertStringToDate(openWeather.getList().get(4).getDt_txt());
            tvDay4.setText(day4.getHours() +"h00");
            imgsun4.setImageBitmap(sunBitmap4);
            tvTem4.setText(subStringTemp(""+openWeather.getList().get(4).getMain().getTemp_max(),2)+"°C/"+subStringTemp(""+openWeather.getList().get(4).getMain().getTemp_min(),2)+"°C");
            tvMain4.setText(openWeather.getList().get(4).getWeather().get(0).getDescription());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.progressDialog.dismiss();
        super.onPostExecute(openWeather);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Bitmap getSunBitmap() {
        return sunBitmap;
    }

    public void setSunBitmap(Bitmap sunBitmap) {
        this.sunBitmap = sunBitmap;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    private String subStringTemp(String temp, int point){
        temp = temp.substring(0,point);
        return temp;
    }

    private Bitmap loadSunImage(String idIcon){
        Bitmap sunBit = null;
        try {
            String urlIcon = "http://openweathermap.org/img/w/" + idIcon + ".png";
            URL urlConection = new URL(urlIcon);
            HttpURLConnection connection = (HttpURLConnection)urlConection.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream input = connection.getInputStream();

            sunBit = BitmapFactory.decodeStream(input);
        } catch (Exception e){
            e.printStackTrace();
        }
        return sunBit;
    }
    private Date convertStringToDate(String day) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        date = sdf.parse(day);
        return date;
    }

    private void OpenDialogMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Error");
        builder.setMessage("No Ineternet connection!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                activity.finish();
                //Đồng ý thì thoát chương trình
                System.exit(0);
            }
        });
        builder.setNegativeButton("OK",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    private int loadBackground(String local){
        int drawAbleId = R.drawable.bp32;
        if(local.equals("Ha Noi") || local.equals("Xóm Pho")){
            drawAbleId = R.drawable.hanoiweather4;
        }
        if(local.equals("Thanh pho Ho Chi Minh") || local.equals("Thành phố Hồ Chí Minh")){
            drawAbleId = R.drawable.saigonweather;
        }
        if(local.equals("Haiphong")){
            drawAbleId = R.drawable.haiphongweather;
        }
        if(local.equals("Bac Ninh")){
            drawAbleId = R.drawable.bacninhweahter;
        }
        return drawAbleId;
    }

}
