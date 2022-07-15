package com.example.a5broadcast;

import android.app.Application;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OfflineReceiver receiver = new OfflineReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("com.example.a5broadcast.offline"));
        registerActivityLifecycleCallbacks(new Callback());
    }

}
