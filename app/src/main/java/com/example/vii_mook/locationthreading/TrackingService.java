package com.example.vii_mook.locationthreading;

import android.app.Service;
import android.content.Intent;
import android.location.LocationListener;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by vii-mook on 8/24/2017 AD.
 */

public class TrackingService extends Service {
    final static String LOGTAG = "Location Monitoring";

    //Start tracking service
    public final static String ACTION_START_MONITORING = "com.vipavee.START_MONITORING";
    //Stop tracking service
    public final static String ACTION_STOP_MONITORING = "com.vipavee.STOP_MONITORING";
    private final static String HANDLER_THREAD_NAME = "MyLocationThread";

    LocationListener locationListener;
    Looper looper;
    android.os.Handler handler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOGTAG, "Location Monitoring Service onStartCommand - " + LogHelper.threadId());

        String action = intent.getAction();
        Log.d(LOGTAG, "Location Service onStartCommand Action: " + LogHelper.threadId());

        if (action.equals(ACTION_START_MONITORING)) {
            doStartTracking();

        } else if (action.equals(ACTION_STOP_MONITORING)) {
            doStopTracking();
            stopSelf();
        }
        return START_STICKY;
    }

    private void doStartTracking() {
    }

    private void doStopTracking() {
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
