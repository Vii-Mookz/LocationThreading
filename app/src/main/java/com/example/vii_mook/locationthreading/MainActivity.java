package com.example.vii_mook.locationthreading;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {
    final static String LOGTAG = "Location Monitoring";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOGTAG,"Location Monitoring MAIN -" + LogHelper.threadId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    public void onMenuTrackingActivityWithFragment(MenuItem item) {
        startActivity(new Intent(this,TrackingActivityWithFragment.class));

    }

    public void onMenuTrackingActivityOnly(MenuItem item) {
        startActivity(new Intent(this,TrackingActivity.class));
    }

    public void onMenuStartTrackingService(MenuItem item) {
        startService(new Intent(TrackingService.ACTION_START_MONITORING));
    }

    public void onMenuStopTrackingService(MenuItem item) {
        startService(new Intent(TrackingService.ACTION_STOP_MONITORING));
    }

    public void onExit(MenuItem item) {
        Log.d(LOGTAG, "Exit menu selected");
        finish();
    }
}
