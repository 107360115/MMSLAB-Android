package com.example.bob.server_test;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private TextView run_time; // 秒數顯示結果
    private BroadcastReceiver myBroadcasReceiver; // 廣播接收器

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.send);
        run_time = (TextView) findViewById(R.id.message);

        btn_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(MainActivity.this, MyService.class);
                startService(i);

                Toast.makeText(MainActivity.this, "Service啟動成功", Toast.LENGTH_SHORT)
                        .show();
            }
        });

         myBroadcasReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle myBundle = intent.getExtras();
                int myInt = myBundle.getInt("background_service");

                run_time.setText("後台Service執行了" + myInt + "秒");
            }
        };
        IntentFilter intentFilter = new IntentFilter("MyMessage");
        registerReceiver(myBroadcasReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcasReceiver);
    }


}