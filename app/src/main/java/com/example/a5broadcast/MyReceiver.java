package com.example.a5broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Toast.makeText(context, "mybroadcast!", Toast.LENGTH_SHORT).show();
        Log.e("RECEIVE", " MY BROADCAST");
    }
}