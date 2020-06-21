package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TestBatteryActivity extends AppCompatActivity {
    BatteryChangReceiver batteryChangReceiver;
//    Button btn_register;
//    Button btn_unregisyter;
    TextView tv_heath;
    TextView tv_level;
    TextView tv_maxlevel;
    TextView tv_pluged;
    TextView tv_status;
    TextView tv_other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_heath=(TextView) findViewById(R.id.tv_heath);
        tv_level=(TextView) findViewById(R.id.tv_level);
        tv_maxlevel=(TextView) findViewById(R.id.tv_maxlevel);
        tv_pluged=(TextView) findViewById(R.id.tv_pluged);
        tv_status=(TextView) findViewById(R.id.tv_status);
        tv_other=(TextView) findViewById(R.id.tv_other);

        BatteryChangReceiver batteryChangReceiver=new BatteryChangReceiver();
//        btn_register=(Button) findViewById(R.id.btn_register);
//        btn_unregisyter=(Button) findViewById(R.id.btn_unregisyter);
//        btn_unregisyter.setEnabled(false);
        IntentFilter intentFilter=getFilter();
       registerReceiver(batteryChangReceiver,intentFilter);
    }

     protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(batteryChangReceiver);
    }
////注册电量监听
//    public void registerBatter(View view){
//        IntentFilter intentFilter=getFilter();
//        registerReceiver(batteryChangReceiver,intentFilter);
//        btn_register.setEnabled(false);
//        btn_unregisyter.setEnabled(true);
//        Toast.makeText(this,"电量变化的receiver注册成功",Toast.LENGTH_LONG).show();
//    }
////取消电量监听
//    public void unregisterBatter(View view){
//        unregisterReceiver(batteryChangReceiver);
//        btn_register.setEnabled(true);
//        btn_unregisyter.setEnabled(false);
//        Toast.makeText(this,"电量变化的receiver已经取消",Toast.LENGTH_LONG).show();
//    }
    //获取IntenFilter对象
    public IntentFilter getFilter() {
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        return filter;
    }

    public class BatteryChangReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String  action=intent.getAction();
            if(action.equalsIgnoreCase(Intent.ACTION_BATTERY_CHANGED)){
                //当前电压
                int voltage=intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                //电池健康状态
                int health=intent.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
                switch (health){
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        tv_heath.setText("很好");
                    case  BatteryManager.BATTERY_HEALTH_COLD:
                        tv_heath.setText("BATTERY_HEALTH_COLD");
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        tv_heath.setText("BATTERY_HEALTH_DEAD");
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        tv_heath.setText("BATTERY_HEALTH_OVER_VOLTAGE");
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        tv_heath.setText("BATTERY_HEALTH_OVERHEAT");
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        tv_heath.setText("BATTERY_HEALTH_UNKNOWN");
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        tv_heath.setText("BATTERY_HEALTH_UNSPECIFIED_FAILURE");
                        break;
                    default:
                        break;

                }

                //当前的电量
                int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
                tv_level.setText(String.valueOf(level));

                //最大电量
                int scale=intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
                tv_maxlevel.setText(String.valueOf(scale));

                //当前手机使用的电源类型
                int plugged=intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
                switch(plugged){
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        tv_pluged.setText("电源是来自充电器");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        tv_pluged.setText("电源来自USB");
                        break;
                    default:
                        break;
                }

                int status=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
                switch (status){
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        tv_status.setText("正在充电");
                        break;
                    case  BatteryManager.BATTERY_STATUS_DISCHARGING:
                        tv_status.setText("BATTERY_STATUS_DISCHARGING");
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        tv_status.setText("已经充满");
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        tv_status.setText("没有充电");
                        break;
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        tv_status.setText("未知状态");
                        break;
                    default:
                        break;

                }

                //当前电池温度
                int temperature=intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1);
                    String str="voltage="+voltage +"temperature="+temperature;
                    tv_other.setText(str);
            }else if(action.equalsIgnoreCase(Intent.ACTION_BATTERY_LOW)){
                System.out.println("电量不足");
            }else if(action.equalsIgnoreCase(Intent.ACTION_BATTERY_OKAY)){
                System.out.println("电池已从电量低恢复正常");
            }
        }
    }


}
