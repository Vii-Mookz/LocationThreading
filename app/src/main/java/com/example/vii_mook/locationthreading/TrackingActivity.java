package com.example.vii_mook.locationthreading;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class TrackingActivity extends Activity {
    final static String LOGTAG = "Location Monitoring";

    Looper looper;
    MainActivityLocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tracking_menu, menu);
        return true;
    }


    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuStartTracking:
                onMenuStartTracking(item);
                break;
            case R.id.menuStopTracking:
                onMenuStopTracking(item);
                break;
        }

        return true;
    }

    private void onMenuStartTracking(MenuItem item) {
        doStartTracking();
    }

    private void onMenuStopTracking(MenuItem item) {
        doStopTracking();
    }

    public void setLocation(Location location) {
        Log.d(LOGTAG, "Location Monitoring Activity setLocation - " + LogHelper.threadId());

        String latitudeString = String.format("%.6f", location.getLatitude());
        String longitudeString = String.format("%.6f", location.getLongitude());

        try {
            TextView latitudeView = (TextView) findViewById(R.id.textViewLatitude);
            TextView longitudeView = (TextView) findViewById(R.id.textViewLongitude);

            latitudeView.setText(latitudeString);
            longitudeView.setText(longitudeString);
        } catch (Exception ex) {
            Log.e(MainActivity.LOGTAG, "Location Monitor setLocation Exception", ex);
        }
    }

    private void doStartTracking() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new MainActivityLocationListener();
        locationListener.setTrackingActivity(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

    private void doStopTracking() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationListener != null) {
            lm.removeUpdates(locationListener);
            locationListener = null;
        }

    }

    class LocationState {
        Looper looper;
        MainActivityLocationListener locationListener;

        LocationState(Looper looper, MainActivityLocationListener locationListener) {
            looper = looper;
            locationListener = locationListener;
        }

        Looper getLooper() {
            return looper;
        }

        MainActivityLocationListener getLocationListener() {
            return locationListener;
        }
    }
}