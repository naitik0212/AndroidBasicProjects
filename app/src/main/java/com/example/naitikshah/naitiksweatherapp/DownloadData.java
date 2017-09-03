/**
 * Created by naitikshah on 9/2/17.
 */
package com.example.naitikshah.naitiksweatherapp;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.*;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.HttpURLConnection;

public class DownloadData extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... urls) {

        String Result =  "";
        URL link;
        HttpURLConnection urlConnection=null;

        try {
            link=new URL(urls[0]);

            urlConnection=(HttpURLConnection) link.openConnection();

            InputStream input=urlConnection.getInputStream();

            InputStreamReader reader= new InputStreamReader(input);

            int data=reader.read();

            while (data != -1){

                char current = (char)data;
                Result=Result+current;
                data=reader.read();
            }return Result;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    protected void onPostExecute(String Result){

        super.onPostExecute(Result);

        try {

            JSONObject jsonObject=new JSONObject(Result);

            //String weatherInfo=jsonObject.getString("weather");

            JSONObject weatherData=new JSONObject(jsonObject.getString("main"));

            double temp= Double.parseDouble(weatherData.getString("temp"));


            int temperatureinCelsius= (int) (temp - 273.15);
            int temperatureinFarenheit=(int)  (temp* 9/5 - 459.67);

            String placeName=jsonObject.getString("name");


            MainActivity.placeTextview.setText(placeName);

            MainActivity.TemperatureTextView.setText(String.valueOf(temperatureinCelsius));





        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

