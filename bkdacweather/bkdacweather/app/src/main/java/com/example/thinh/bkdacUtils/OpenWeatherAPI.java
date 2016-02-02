package com.example.thinh.bkdacUtils;

import com.example.thinh.bkdacModel.OpenWeather;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by thinh on 30/09/2015.
 */
public class OpenWeatherAPI {

    public static OpenWeather loadJson(String localUrl){
        try {
            String location = URLEncoder.encode(localUrl,"UTF-8");
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q="+location+"&cnt=5");
            InputStreamReader reader = new InputStreamReader(url.openStream(),"UTF-8");
            OpenWeather results = new Gson().fromJson(reader,OpenWeather.class);

            return results;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
