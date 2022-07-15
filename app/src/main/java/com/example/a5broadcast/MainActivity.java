package com.example.a5broadcast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Callback callback = new Callback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et1 = (EditText) findViewById(R.id.accountEdit);
        EditText et2 = (EditText) findViewById(R.id.passwordEdit);
        Button b = (Button) findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().equals("ckf") && et2.getText().toString().equals("123456")) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
            }
        });
        Log.e("activity1", " taskid: " + getTaskId());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("activity1", " new intent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplication().unregisterActivityLifecycleCallbacks(callback);
    }
}

class OfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.e("PANKAJ", "Context class " + context.getClass().getName());
        AlertDialog.Builder builder = new AlertDialog.Builder(Callback.currentActivity);
        builder.setMessage("offline");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("activity", "number " + Callback.actList.size());
                Callback.removeAll();
                Intent i = new Intent(context, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        builder.show();
    }
}

class Callback implements Application.ActivityLifecycleCallbacks {
    public static Activity currentActivity = null;
    public static List<Activity> actList = new ArrayList<Activity>();

    public static void removeAll() {
        for (Activity act : actList) {
            if (!act.isFinishing()) {
                act.finish();
            }
        }
        actList.clear();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        actList.add(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        if (currentActivity == activity) {
            currentActivity = null;
        }
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        actList.remove(activity);
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }
}