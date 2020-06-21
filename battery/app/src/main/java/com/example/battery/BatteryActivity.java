package com.example.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BatteryActivity extends AppCompatActivity {
    private IntentFilter intentFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(intentFilterReceiver,intentFilter);
    }

    protected void onResume(){
        super.onResume();

    }

    private BroadcastReceiver intentFilterReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                Log.d("Battery",""+intent.getIntExtra("level",0));

                Log.d("Battery",""+intent.getIntExtra("scale",0));

                Log.d("Battery",""+intent.getIntExtra("voltage",0));
                Log.d("Battery",""+intent.getIntExtra("temperature",0));

                Log.d("电池状态","--------");

                Log.d("Battery",""+intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN));

                Log.d("充电类型","--------");

                Log.d("Battery",""+intent.getIntExtra("plugged",0));


            }
        }
    };

    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(intentFilterReceiver);
    }
}
