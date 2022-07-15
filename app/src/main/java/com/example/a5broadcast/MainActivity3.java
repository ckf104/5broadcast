package com.example.a5broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        setContentView(R.layout.activity_main3);
        Button b = (Button) findViewById(R.id.forceOffline);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.a5broadcast.offline");
                broadcastManager.sendBroadcast(intent);
            }
        });
    }
}