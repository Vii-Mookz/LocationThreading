package com.example.vii_mook.locationthreading;


import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


public class TrackingFragment extends Fragment implements LocationListener {
    final static String LOGTAG = "Location Monitoring";

    View _topView;
    Looper _looper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        doStopTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _topView = inflater.inflate(R.layout.fragment_tracking, container, false);

        return _topView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tracking_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    private void doStartTracking() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        HandlerThread thread = new HandlerThread("locationthread");
        thread.start();
        _looper = thread.getLooper();

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this,_looper);


    }

    private void doStopTracking() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        lm.removeUpdates(this);

        if (_looper != null) {
            _looper.quit();
            _looper = null;
        }
    }

    public void setLocation(Location location) {
        Log.d(LOGTAG, "Location Monitoring Fragment setLocation - " + LogHelper.threadId());

        String latitudeString = String.format("%.6f", location.getLatitude());
        String longitudeString = String.format("%.6f", location.getLongitude());

        try{
            TextView latitudeView = (TextView) _topView.findViewById(R.id.textViewLatitude);
            TextView longitudeView = (TextView) _topView.findViewById(R.id.textViewLongitude);

            latitudeView.setText(latitudeString);
            longitudeView.setText(longitudeString);
        }
        catch (Exception ex) {
            Log.e(MainActivity.LOGTAG, "Location Monitor setLocation Exception", ex);
        }
    }

    public void onLocationChanged(Location location) {
        Log.d(LOGTAG, "Location Monitoring Fragment onLocationChanged -" + LogHelper.threadId());

        final Location theLocation = location;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setLocation(theLocation);
            }
        });


    }

    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    public void onProviderEnabled(String s) {

    }

    public void onProviderDisabled(String s) {

    }
}