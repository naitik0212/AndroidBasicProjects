package com.example.naitikshah.naitiksweatherapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.location.*;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
import android.view.Menu;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    static TextView placeTextview;
    static TextView TemperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeTextview= (TextView)findViewById(R.id.nameTextView);
        TemperatureTextView= (TextView)findViewById(R.id.TemperatureTextView);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String provider = locationManager.getBestProvider(new Criteria(), false);


        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }

        Location location = locationManager.getLastKnownLocation(provider);

        double lat=location.getLatitude();
        double log=location.getLongitude();


        DownloadData task =new DownloadData();

        task.execute("http://samples.openweathermap.org/dat a/2.5/weather?lat="+ String.valueOf(lat) + "&lon="+ String.valueOf(log) +" &appid=c6ff0988c4b98c01ccb14f9a6f6a2f5f");



    }
}
