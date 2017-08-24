package com.example.vii_mook.locationthreading;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by vii-mook on 8/24/2017 AD.
 */

public class MyServiceLocationListener implements LocationListener {
    final static String LOGTAG = "Location Monitoring";
    @Override
    public void onLocationChanged(Location location) {
        Log.d(LOGTAG, "Location Monitoring Service onLocationChanged - " + LogHelper.threadId());

        String logMag = LogHelper.formatLocationInfo(location);
        Log.d(LOGTAG, "Location Monitoring -" + logMag);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
