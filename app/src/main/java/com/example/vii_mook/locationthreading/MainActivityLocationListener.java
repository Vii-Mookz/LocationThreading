package com.example.vii_mook.locationthreading;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by vii-mook on 8/24/2017 AD.
 */

public class MainActivityLocationListener implements LocationListener {
    final static String LOGTAG = "Location Monitoring";
    TrackingActivity trackingActivity;

    public void setTrackingActivity(TrackingActivity trackingActivity) {
        this.trackingActivity = trackingActivity;
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.d(LOGTAG, "Location Monitoring Activity onLocationChange -" + LogHelper.threadId());

        final Location theLocation = location;
        trackingActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                trackingActivity.setLocation(theLocation);
            }
        });

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
