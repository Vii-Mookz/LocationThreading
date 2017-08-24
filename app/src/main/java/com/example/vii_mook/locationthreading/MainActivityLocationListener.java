package com.example.vii_mook.locationthreading;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

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
