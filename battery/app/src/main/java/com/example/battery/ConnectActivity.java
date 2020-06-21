package com.example.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConnectActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        NeteworkChangeReceiver networkChangeReceiver=new NeteworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }

    class NeteworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"网络发生了变化",Toast.LENGTH_SHORT).show();


            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context,"网络已经连接",Toast.LENGTH_SHORT).show();
                Log.d("网络连接了嘛？","网络已经连接");
                if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                    Log.d("网络连接类型：","WIFI");
                }
                else if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                    Log.d("网络连接类型：","移动数据");
                }
            }else{
                Toast.makeText(context,"网络未连接",Toast.LENGTH_SHORT).show();
                Log.d("网络连接了嘛？","网络未连接");
            }

        }
    }
}
